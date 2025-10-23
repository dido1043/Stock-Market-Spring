package com.example.stockmarketspringapi.client.interfaces;

import com.example.stockmarketspringapi.config.FeignConfig;
import com.example.stockmarketspringapi.exception.FeignErrorDecoder;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(
        name = "Currency",
        url = "http://localhost:8000",
        configuration = FeignConfig.class)
public interface CurrencyClient {

    @GetMapping("/api/currency/get")
    Response getCurrency();
}
