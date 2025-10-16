package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.client.interfaces.CurrencyClient;
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
import feign.Response;
import feign.Util;
import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculateServiceImpl implements CalculationService {
    private final CurrencyClient currencyClient;
    private final StockRepository stockRepository;
    private final CurrencyService currencyService;
    private final ObjectMapper objectMapper;
    private final ModelMapper mapper;
    public CalculateServiceImpl(CurrencyClient currencyClient,
                                StockRepository stockRepository,
                                CurrencyService currencyService,
                                ObjectMapper objectMapper) {
        this.currencyClient = currencyClient;
        this.stockRepository = stockRepository;
        this.currencyService = currencyService;
        this.objectMapper = objectMapper;

        this.mapper = new ModelMapper();
    }

    @Override
    public StockResponseDto calculatePriceInEur() {
        Response client = currencyClient.getCurrency();
        try {
            String currencyData = Util.toString(client.body().asReader(client.charset()));
            List<CurrencyDto> currencyList = objectMapper.readValue(currencyData,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, CurrencyDto.class));

            CurrencyDto eurCurrency = currencyList.stream()
                    .filter(c -> c.getSymbol().equals("EUR"))
                    .findFirst()
                    .orElseThrow(NotFoundException::new);

            BigDecimal currencyAmount = new BigDecimal(eurCurrency.getAmount());

            Stock stock = stockRepository.findAll().getLast();

            StockDto stockDto = new StockDto();

            stockDto.setCompanyId(stock.getCompany().getId());
            stockDto.setMarketCapitalization(stock.getMarketCapitalization().multiply(currencyAmount));
            stockDto.setShareOutstanding(stock.getShareOutstanding().multiply(currencyAmount));


            var status = client.status();
            return mapper.map(stockDto, StockResponseDto.class);
        }catch (Exception e){
            throw new RuntimeException("Failed to parse currency data", e);
        }
    }

    @Override
    public StockDto calculatePriceInUsd() {
        return null;
    }

    @Override
    public StockDto calculatePriceInCny() {
        return null;
    }
    //Handle exceptions

    private double currencyDetermination(Stock stock) {


        return 0;
    }
}
