package com.example.stockmarketspringapi.model.dto;

import java.time.LocalDateTime;

public class CurrencyDto {
    private String symbol;
    private Double amount;
    private LocalDateTime last_price_update;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getLast_price_update() {
        return last_price_update;
    }

    public void setLast_price_update(LocalDateTime last_price_update) {
        this.last_price_update = last_price_update;
    }
}
