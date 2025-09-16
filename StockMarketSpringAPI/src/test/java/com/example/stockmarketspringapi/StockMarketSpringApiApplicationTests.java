package com.example.stockmarketspringapi;

import com.example.stockmarketspringapi.config.DotenvInitializer;
import com.example.stockmarketspringapi.controller.CompanyController;
import com.example.stockmarketspringapi.controller.StockController;
import com.example.stockmarketspringapi.model.entity.Stock;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import com.example.stockmarketspringapi.service.interfaces.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ContextConfiguration(initializers = DotenvInitializer.class)
class StockMarketSpringApiApplicationTests {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StockService stockService;

    @Autowired
    private CompanyController companyController;

    @Autowired
    private StockController stockController;

    @Test
    void contextLoads() {
        assertThat(companyService).isNotNull();
        assertThat(stockService).isNotNull();
        assertThat(companyController).isNotNull();
        assertThat(stockController).isNotNull();
    }

}
