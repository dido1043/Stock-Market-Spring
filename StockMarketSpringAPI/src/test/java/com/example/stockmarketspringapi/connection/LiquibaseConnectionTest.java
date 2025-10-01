package com.example.stockmarketspringapi.connection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LiquibaseConnectionTest {

  @Autowired JdbcTemplate jdbc;

  @Test
  void dbConnectionWorks() {
    Integer one = jdbc.queryForObject("SELECT 1", Integer.class);
    assertEquals(1, one);
  }
}
