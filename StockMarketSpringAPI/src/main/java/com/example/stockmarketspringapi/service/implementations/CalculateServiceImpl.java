package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.client.interfaces.CurrencyClient;
import com.example.stockmarketspringapi.exception.InternalServerException;
import com.example.stockmarketspringapi.exception.NotFoundException;
import com.example.stockmarketspringapi.model.dto.CurrencyDto;
import com.example.stockmarketspringapi.model.dto.StockDto;
import com.example.stockmarketspringapi.model.dto.StockResponseDto;
import com.example.stockmarketspringapi.model.entity.Stock;
import com.example.stockmarketspringapi.repository.StockRepository;
import com.example.stockmarketspringapi.service.interfaces.CalculationService;
import com.example.stockmarketspringapi.service.interfaces.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Body;
import feign.FeignException;
import feign.Response;
import feign.Util;
import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculateServiceImpl implements CalculationService {
    private final CurrencyClient currencyClient;
    private final ObjectMapper objectMapper;
    public CalculateServiceImpl(CurrencyClient currencyClient,
                                ObjectMapper objectMapper) {
        this.currencyClient = currencyClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public double CurrencyCalculate(BigDecimal amount, String sign) {
        try{
            Response client = currencyClient.getCurrency();
            String currencyData = Util.toString(client.body().asReader(client.charset()));
            List<CurrencyDto> currencyList = objectMapper.readValue(currencyData,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, CurrencyDto.class));

            CurrencyDto currency = currencyList.stream()
                    .filter(c -> c.getSymbol().equals(sign))
                    .findFirst()
                    .orElseThrow(NotFoundException::new);

            BigDecimal currencyAmount = new BigDecimal(currency.getAmount());

            return amount.multiply(currencyAmount).doubleValue();
        }catch (IOException e){
            throw new InternalServerException("Failed to parse currency data %s".formatted(e.getMessage()));
        }
    }
}
