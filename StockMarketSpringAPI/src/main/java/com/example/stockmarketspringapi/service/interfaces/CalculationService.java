package com.example.stockmarketspringapi.service.interfaces;

import com.example.stockmarketspringapi.model.dto.StockDto;
import com.example.stockmarketspringapi.model.dto.StockResponseDto;
import com.example.stockmarketspringapi.model.entity.Stock;

public interface CalculationService {

    StockResponseDto calculatePriceInEur();
    StockDto calculatePriceInUsd();
    StockDto calculatePriceInCny();
}
