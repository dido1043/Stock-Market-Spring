package com.example.stockmarketspringapi.client.interfaces;

import com.example.stockmarketspringapi.config.FeignConfig;
import com.example.stockmarketspringapi.model.dto.CurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@FeignClient(
        name = "Currency",
        url = "http://localhost:8000",
        configuration = FeignConfig.class)
public interface CurrencyClient {

    @GetMapping("/api/currency/get")
    List<CurrencyDto> getCurrency();
}
