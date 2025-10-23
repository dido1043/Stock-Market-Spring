package com.example.stockmarketspringapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class
StockMarketSpringApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockMarketSpringApiApplication.class, args);
    }

}
