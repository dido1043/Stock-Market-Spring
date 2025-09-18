package com.example.stockmarketspringapi.service.interfaces;

import com.example.stockmarketspringapi.model.dto.*;

public interface StockService {
    StockResponseDto getStock(Long companyId);
}
