package com.distributed.reservation_system;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BookingSimulation extends Simulation {

    // 1. Define the HTTP Protocol (Point to your running Spring Boot app)
    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // 2. Define a Feeder for 100,000 unique users
    // This prevents "Unique Constraint" errors in your DB baseline
    Iterator<Map<String, Object>> feeder = Stream.generate(() -> {
        long id = ThreadLocalRandom.current().nextLong(511, 100000);
        return Map.<String, Object>of("userId", id);
    }).limit(100000).iterator();

    // 3. Define the Scenario
    ScenarioBuilder scn = scenario("DB Baseline Load Test")
            .feed(feeder)
            .exec(http("Book Ticket Request")
                    .post("/reservation/")
                    // Matches your ReservationRequest structure
                    .body(StringBody("{ \"userId\": #{userId}, \"masterPassengerIdList\": [#{userId}], \"tripId\": 37 }"))
                    .check(status().is(200))
            );

    {
        // 4. Define the Load Profile
        // Ramp up to 100,000 users over 5 minutes to avoid immediate OS port exhaustion
        setUp(
                scn.injectOpen(
                        rampUsers(100000).during(Duration.ofMinutes(10))
                )
        ).protocols(httpProtocol);
    }
}