package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.client.interfaces.CurrencyClient;
import com.example.stockmarketspringapi.exception.errors.BadRequestException;
import com.example.stockmarketspringapi.exception.errors.InternalServerException;
import com.example.stockmarketspringapi.exception.errors.NotFoundException;
import com.example.stockmarketspringapi.model.dto.CurrencyDto;
import com.example.stockmarketspringapi.service.interfaces.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculateServiceImpl implements CalculationService {
    private final CurrencyClient currencyClient;
    private final Logger log = LoggerFactory.getLogger(CalculateServiceImpl.class);

    public CalculateServiceImpl(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Override
    public double CurrencyCalculate(BigDecimal amount, String sign) {

            List<CurrencyDto> currencyList = currencyClient.getCurrency();

            CurrencyDto currency = currencyList.stream()
                    .filter(c -> c.getSymbol().equals(sign))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Currency symbol '%s' not found".formatted(sign)));

            BigDecimal currencyAmount = new BigDecimal(currency.getAmount());

            return amount.multiply(currencyAmount).doubleValue();

    }
}
