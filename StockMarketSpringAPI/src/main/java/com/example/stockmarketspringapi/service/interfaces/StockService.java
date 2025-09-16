package com.example.stockmarketspringapi.service.interfaces;

import com.example.stockmarketspringapi.model.dto.StockDto;

public interface StockService {
    StockDto getStock(Long companyId);
}
