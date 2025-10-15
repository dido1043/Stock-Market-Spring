package com.example.stockmarketspringapi.client.interfaces;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FeignClient(name = "Currency", url = "http://localhost:8000")
public interface CurrencyClient {
    // to fix
    @GetMapping("/api/currency/get")
    Response getCurrency();
}
