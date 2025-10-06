package com.example.stockmarketspringapi.service.interfaces;

import com.example.stockmarketspringapi.model.dto.*;
import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.model.entity.Stock;

public interface StockService {
    StockResponseDto getStock(Long companyId);
    StockDto mapToDtoForExistingStock(Stock stock, Company company);
    StockDto convertToStockDto(FinnhubStockDto stockData, Company company);
}
