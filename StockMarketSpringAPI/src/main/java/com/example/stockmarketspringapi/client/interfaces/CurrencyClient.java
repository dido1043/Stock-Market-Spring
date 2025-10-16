package com.example.stockmarketspringapi.client.interfaces;

import com.example.stockmarketspringapi.config.FeignConfig;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@FeignClient(
        name = "Currency",
        url = "http://localhost:8000",
        configuration = FeignConfig.class)
public interface CurrencyClient {

    @GetMapping("/api/currency/get")
    Response getCurrency();
}
