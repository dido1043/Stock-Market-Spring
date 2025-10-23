package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.client.interfaces.CurrencyClient;
import com.example.stockmarketspringapi.exception.errors.InternalServerException;
import com.example.stockmarketspringapi.exception.errors.NotFoundException;
import com.example.stockmarketspringapi.model.dto.CurrencyDto;
import com.example.stockmarketspringapi.service.interfaces.CalculationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service

public class CalculateServiceImpl implements CalculationService {
    private final CurrencyClient currencyClient;
    private final ObjectMapper objectMapper;
    private final Logger log = LoggerFactory.getLogger(CalculateServiceImpl.class);
    public CalculateServiceImpl(CurrencyClient currencyClient,
                                ObjectMapper objectMapper) {
        this.currencyClient = currencyClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public double CurrencyCalculate(BigDecimal amount, String sign) {
        try{
            // FeignErrorDecoder will handle HTTP errors (4xx, 5xx) from the currency service
            Response client = currencyClient.getCurrency();
            String currencyData = Util.toString(client.body().asReader(client.charset()));
            List<CurrencyDto> currencyList = objectMapper.readValue(currencyData,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, CurrencyDto.class));

            CurrencyDto currency = currencyList.stream()
                    .filter(c -> c.getSymbol().equals(sign))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Currency symbol '%s' not found".formatted(sign)));

            BigDecimal currencyAmount = new BigDecimal(currency.getAmount());

            return amount.multiply(currencyAmount).doubleValue();
        } catch (IOException e) {
            // Handle JSON parsing errors
            log.error("Failed to parse currency data for symbol '{}': {}", sign, e.getMessage());
            throw new InternalServerException(
                    "Failed to parse currency data: %s".formatted(e.getMessage()),
                    e
            );
        } catch (InternalServerException | NotFoundException e) {
            // Re-throw custom exceptions (from FeignErrorDecoder or business logic)
            throw e;
        } catch (Exception e) {
            // Catch any unexpected errors
            log.error("Unexpected error during currency calculation: {}", e.getMessage(), e);
            throw new InternalServerException(
                    "Unexpected error during currency calculation: %s".formatted(e.getMessage()),
                    e
            );
        }
    }
}
