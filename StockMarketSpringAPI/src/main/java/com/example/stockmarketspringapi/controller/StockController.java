package com.example.stockmarketspringapi.controller;

import com.example.stockmarketspringapi.model.dto.StockResponseDto;
import com.example.stockmarketspringapi.service.interfaces.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @GetMapping("/company-stocks/{id}")
    public ResponseEntity<StockResponseDto> getStock(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getStock(id));


    }

}
