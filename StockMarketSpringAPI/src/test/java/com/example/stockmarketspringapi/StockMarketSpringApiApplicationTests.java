package com.example.stockmarketspringapi;

import com.example.stockmarketspringapi.config.DotenvInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ContextConfiguration(initializers = DotenvInitializer.class)
class StockMarketSpringApiApplicationTests {

    @Autowired
    private StockMarketSpringApiApplication application;
    @Test
    void contextLoads() {
        assertThat(application).isNotNull();
    }

}
