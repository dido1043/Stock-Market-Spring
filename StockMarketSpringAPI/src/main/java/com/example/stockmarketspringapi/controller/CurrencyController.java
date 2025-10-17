package com.example.stockmarketspringapi.controller;

import com.example.stockmarketspringapi.model.dto.CurrencyDto;
import com.example.stockmarketspringapi.model.dto.StockDto;
import com.example.stockmarketspringapi.model.dto.StockResponseDto;
import com.example.stockmarketspringapi.service.implementations.CalculateServiceImpl;
import com.example.stockmarketspringapi.service.interfaces.CalculationService;
import com.example.stockmarketspringapi.service.interfaces.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CalculationService calculationService;
    public CurrencyController(CurrencyService currencyService,
                              CalculationService calculationService) {
        this.calculationService = calculationService;
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<CurrencyDto>> getCurrency() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }
}
