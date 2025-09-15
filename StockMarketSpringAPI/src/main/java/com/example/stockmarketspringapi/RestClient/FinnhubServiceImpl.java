package com.example.stockmarketspringapi.RestClient;

import com.example.stockmarketspringapi.RestClient.interfaces.FinnhubService;
import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FinnhubServiceImpl implements FinnhubService {

    private final String apiUrl = "https://finnhub.io/api/v1/stock/profile2";

//    private final WebClient webClient;
//
//    public FinnhubServiceImpl() {
//        this.webClient = WebClient.builder().baseUrl(apiUrl).build();
//    }

    @Override
    public FinnhubStockDto getApiData(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?" + "symbol=" + symbol + "&" + "token=" + "d2olq21r01qga5g9s8qgd2olq21r01qga5g9s8r0";
        return  restTemplate.getForObject(url, FinnhubStockDto.class);
    }
}
