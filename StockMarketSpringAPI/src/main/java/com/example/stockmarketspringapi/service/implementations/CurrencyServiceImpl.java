package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.client.interfaces.CurrencyClient;
import com.example.stockmarketspringapi.model.dto.CurrencyDto;
import com.example.stockmarketspringapi.service.interfaces.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyClient currencyClient;
    private final ObjectMapper objectMapper;

    public CurrencyServiceImpl(CurrencyClient currencyClient, ObjectMapper objectMapper) {
        this.currencyClient = currencyClient;
        this.objectMapper = objectMapper;
    }
    @Override
    public List<CurrencyDto> getAllCurrencies() {
        Response response = currencyClient.getCurrency();
        try{
            String responseBody = Util.toString(response.body().asReader());
            return objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, CurrencyDto.class));
        }catch (IOException e){
            throw new RuntimeException("Failed to parse currency data", e);
        }
    }
}
