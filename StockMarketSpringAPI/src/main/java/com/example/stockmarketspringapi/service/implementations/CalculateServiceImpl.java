package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.client.interfaces.CurrencyClient;
import com.example.stockmarketspringapi.model.entity.Stock;
import com.example.stockmarketspringapi.repository.StockRepository;
import com.example.stockmarketspringapi.service.interfaces.CalculationService;
import feign.Response;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculationService {
    private final CurrencyClient currencyClient;
    private final StockRepository stockRepository;
    public CalculateServiceImpl(CurrencyClient currencyClient,
                                StockRepository stockRepository) {
        this.currencyClient = currencyClient;
        this.stockRepository = stockRepository;
    }

    @Override
    public double calculatePriceInEur() {
        Response client = currencyClient.getCurrency();
        client.status();
        //Stock stock = stockRepository.findAll().getLast();
       // Object currencyObj =

        return 0;
    }

    @Override
    public double calculatePriceInUsd() {
        return 0;
    }

    @Override
    public double calculatePriceInCny() {
        return 0;
    }
    //Handle exceptions

    private double currencyDetermination(Stock stock) {


        return 0;
    }
}
