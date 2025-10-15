package com.example.stockmarketspringapi.service.interfaces;

import com.example.stockmarketspringapi.model.entity.Stock;

public interface CalculationService {

    double calculatePriceInEur();
    double calculatePriceInUsd();
    double calculatePriceInCny();
}
