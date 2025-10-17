package com.example.stockmarketspringapi.service.interfaces;

import com.example.stockmarketspringapi.model.dto.StockDto;
import com.example.stockmarketspringapi.model.dto.StockResponseDto;
import com.example.stockmarketspringapi.model.entity.Stock;

import java.math.BigDecimal;

public interface CalculationService {
    double  CurrencyCalculate(BigDecimal amount, String sign);
}
