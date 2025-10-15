package com.example.stockmarketspringapi.controller;

import com.example.stockmarketspringapi.client.interfaces.FinnhubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FinnhubController {

    private final FinnhubService finnhubService;
    @Autowired
    public FinnhubController(FinnhubService finnhubService) {
        this.finnhubService = finnhubService;
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<?> getFinnhubData(@PathVariable String symbol) {
        return ResponseEntity.status(HttpStatus.OK).body(finnhubService.getApiData(symbol));
    }
}
