package com.distributed.reservation_system.util;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseCleanupListener {

    @Autowired
    private DataSource dataSource;

    @PreDestroy
    public void shutdown() {
        if (dataSource instanceof HikariDataSource) {
            ((HikariDataSource) dataSource).close();
            System.out.println("HikariCP Pool closed successfully.");
        }
    }
}