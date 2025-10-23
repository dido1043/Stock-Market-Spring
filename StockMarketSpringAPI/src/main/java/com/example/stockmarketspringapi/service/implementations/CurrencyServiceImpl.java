package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.client.interfaces.CurrencyClient;
import com.example.stockmarketspringapi.exception.errors.BadRequestException;
import com.example.stockmarketspringapi.exception.errors.InternalServerException;
import com.example.stockmarketspringapi.exception.errors.NotFoundException;
import com.example.stockmarketspringapi.model.dto.CurrencyDto;
import com.example.stockmarketspringapi.service.interfaces.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyClient currencyClient;
    private final Logger log = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    public CurrencyServiceImpl(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        return currencyClient.getCurrency();
    }
}
