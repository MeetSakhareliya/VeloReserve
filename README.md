Here are the notes formatted into clean, readable prose:
https://chatgpt.com/share/69ebab54-3274-83a5-8b8b-89d60b47860e
---

## Distributed Systems — Learnings & Key Concepts

### 1. Entity & JPA

Lazy and Eager loading both suffer from the N+1 problem. This can be solved using JOIN FETCH queries or Entity Graph queries. Bidirectional references are better than two unidirectional references. Bidirectional mapping is configured using `mappedBy=variableName`. Without it, unidirectional references with `@JsonIgnore` create double-column or double-tap database problems.

`xToOne` relationships default to Eager loading but should be kept Lazy. `xToMany` relationships are Lazy by default. `@JoinColumn` creates the foreign key column on the "many" side — for example, a Reservation → Passenger relationship places the column on the passenger table.

`GenerationType` has multiple options: `IDENTITY` increments by one each time via the database; `SEQUENCE` does bulk generation from the database for future use; `UUID` generates a globally unique ID.

Use `@Getter` and `@Setter` explicitly. Avoid `@Data` as it can cause issues with bidirectional references and equals/hashCode. For referenced variables, you do not need a fully populated object — you can create a proxy object using the repository class that contains only the foreign key.

---

### 2. Transactions & Locking

Service-level code that only fetches and returns data may not need `@Transactional`. Every other method — including those that call `x.getPassenger()`, perform write operations, need fallback support, or require locking — must be annotated with `@Transactional` at the service method level. Without it, every individual database call is isolated and may produce errors.

A lock is released only when the method annotated with `@Transactional` completes. In raw SQL, a lock can be applied using `SELECT * FROM table FOR UPDATE`.

In a distributed system (microservices/cloud), never rely on Java-level synchronization for data consistency. Java's `synchronized` only works within a single JVM. For example, if App Instance 1 on port 8081 enters a synchronized `bookTicket()` block for Train #5, App Instance 2 on port 8082 has its own memory and its own "door." It does not know Instance 1 is busy. Both instances will read "1 seat left" independently and cause overbooking. Always use a shared resource like the database for coordination.

A method annotated with `@Transactional` must not be called from within the same class. Transactional behavior is handled by a proxy that creates a new database connection, starts the transaction, commits, or rolls back. Calling it from the same class bypasses the proxy entirely.

**Pessimistic locking** assumes collisions will occur and locks from the start — it is slower. **Optimistic locking** assumes collisions are rare and handles them when they occur via a `@Version` field — it offers higher performance.

---

### 3. Request/Response DTOs

Entities should not be used as DTOs for three reasons. First, security: you do not want to expose additional fields that are part of the entity. Second, circular dependency: entities often have bidirectional references that will cause infinite loops when serialized. Third, database schema changes frequently, but DTOs should remain stable.

Only send a full sub-DTO if it is truly needed. For example, in a `ReservationResponse`, you can send just the `userId` instead of the entire User object, since the full user is not needed on the reservation page. More importantly, UserService and ReservationService may be — or in the future will be — separate microservices, and fetching that data would require a gRPC call across service boundaries.

---

### 4. POM & Maven

Parent POMs are primarily for version management. Having a dependency in the parent POM does not mean it will be included in your project. `spring-boot-starter-web` is the primary dependency for a Spring Boot application.

---

### 5. MapStruct Mapper

All nested-level mappings can be defined in a single mapper interface. For example:

```java
@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(source = "trainRun.train.name", target = "trainRun.trainName")
    @Mapping(source = "trainRun.train.sourceStation", target = "trainRun.sourceStation")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "payment.id", target = "payment.id")
    ReservationResponse toResponse(ReservationEntity entity);

    List<ReservationPassengerResponseDTO> toPassengerDtos(List<PassengerEntity> entities);
}
```

Alternatively, nested mappings can be split across separate interfaces. The `@Valid` annotation triggers request validation. Failures can be caught globally in an exception advice class using `MethodArgumentNotValidException`.

**Pre-Redis Testing:** With default Hikari pool settings, running reservation requests for 500 users with a lock acquisition timeout of 5000ms resulted in 10 requests acquiring the lock while the other 490 failed after 30 seconds — meaning those 10 requests each took over 30 seconds (which is the default Hikari thread wait time).

If Payment runs in a separate transaction, all threads will block until connection timeout or lock timeout, then proceed one at a time — success rate will be very low. Reducing the connection timeout slightly frees up threads faster, but success remains low.

If Payment shares the same transaction, blocking only lasts until lock timeout. Increasing lock timeout can reduce failed threads and improve success count.

An unexpected row update issue was resolved by adding `entityManager.refresh(trainTrip)` before `trainRepository.save()`. For the payment isolation issue, the original transaction was suspended temporarily so the same thread could start a new transaction for payment, then resume the original transaction upon completion.

---

### 6. Redis

Two primary reasons to add Redis before the database layer:

**Database meltdown prevention:** When 100k+ users hit booking simultaneously, slow and heavy database queries will eventually bring the system down. Redis acts as a fast first gate.

**Double booking prevention:** Redis's decrement operation is atomic, so even in a distributed environment it prevents race conditions.

Redis forces 99% of requests to fail fast (seat not available), so only the "winners" proceed to the slower database operation.

**Two primary places to warm caches:**
1. In a common service — but since the main service has multiple pods, all pods will try to load the same keys from a single server, causing useless duplicate database calls.
2. In a dedicated cache warm service with only one pod — no redundant database calls.

**Ways to load data on startup:**
- `CommandLineRunner`: Runs after bean creation and just before the application starts. The application will not start until initialization finishes.
- `ApplicationReadyEvent`: Runs after the application has fully started.
- `@PostConstruct`: Only viable if the logic depends solely on that class. Since the cache warmer depends on `redissonClient` and `TrainTripRepository`, this is not suitable here.

**Seat checking with Redis:**

```java
long remaining = redisSeats.addAndGet(-request.getPassengerIds().size());
if (remaining < 0) {
    redisSeats.addAndGet(request.getPassengerIds().size());
    throw new RuntimeException("No seats available in Redis!");
}
```

This code is problematic. Suppose 4 seats are available. A request for 6 atomically decrements to -2, detects it, and starts reverting. But before the revert completes, another request for 3 sees the decremented value, thinks no seats are available, and fails — even though the first request is about to restore those seats. The fix is to use a Lua script, which runs atomically on the Redis server and avoids this race condition.

*What is Spring Data Redis?* — A Spring module that provides abstraction over Redis clients, enabling easy use of Redis data structures through templates and repositories.

---

### 7. Kafka

**Acknowledgement (commit) types:**
- `enable-auto-commit: true` — Time-based; commits happen when the next `poll()` call is made.
- `enable-auto-commit: false` — Three manual modes available:
    - `AckMode.RECORD`: Commits after each individual consumer method completes.
    - `AckMode.BATCH`: Commits after the entire batch from one poll is processed.
    - Manual: You must call `ack.acknowledge()` explicitly.

Partitioning and concurrency are managed via `ConcurrentKafkaListenerContainerFactory` and `ProducerFactory`.

---

### 8. RedisClient vs RedissonClient — Rate Limiting (Leaky Bucket / Token Bucket)

`ProxyManager` and `Bucket` abstractions store tokens in Redis. When a request arrives, tokens are first filled in, then checked to be greater than 0, and then decremented.

For `RedissonClient`, when a `StatefulRedisConnection` is opened during bean creation, you should specify `destroyMethod = "close"` so the connection is properly closed when the application shuts down. This is a good practice to prevent resource leaks.

---

### 9. Reconciliation

There is a possibility that the Redis seat counter and the database will fall out of sync. For example: Redis decrements the count early, sends the event to Kafka, but then payment fails or some other error occurs — the Redis count is never restored.

**Solution:** Run a cron job at regular intervals (e.g., every 5 minutes) to refresh Redis values from the database.

**Problem with naive reconciliation:** If high-volume requests are incoming and some are still being processed in the second flow (the Kafka consumer), those pending requests are counted as valid Redis decrements. Even if there have been no new requests for the last 2 minutes, requests sitting in the Kafka queue have not yet been processed.

**Correct formula:**

```
Available = Total - (DB_Confirmed + Redis_Pending)
```

**Example:** 120 requests came in for 200 seats. 20 are still in the Kafka queue; 100 have been processed — 20 failed and 80 succeeded. The current Redis counter shows 80. But the correct available count should be `200 - (80 + 20) = 100`, since the 20 failed ones should be re-available. There is a minor timing issue: in the consumer, the pending count is decremented last. This will not cause overselling, and the discrepancy will be corrected in the next reconciliation cycle.

**Edge case with reconciliation timing:** Suppose there is only 1 total seat. A request comes in — Redis decrements the seat count and increments the pending count. State: Total=1, Available=0, Pending=1. The request reaches the consumer, payment is processed, and simultaneously reconciliation starts. The consumer has updated the DB count but has not yet decremented the Redis pending count. The reconciliation job reads: Total=1, DB count=1, Pending=1 → Available = 1 - (1+1) = -1. Although this means the reconciliation will set available to a value lower than it should be, there will be no overselling. The issue will resolve itself in the next reconciliation cycle.

---