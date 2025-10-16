package com.example.stockmarketspringapi.service.interfaces;

import com.example.stockmarketspringapi.model.dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> getAllCurrencies();
}
