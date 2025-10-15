package com.example.stockmarketspringapi.client;

import com.example.stockmarketspringapi.client.interfaces.FinnhubService;
import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FinnhubServiceImpl implements FinnhubService {

    private final String apiUrl = "https://finnhub.io/api/v1/stock/profile2";


    @Override
    public FinnhubStockDto getApiData(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?" + "symbol=" + symbol + "&" + "token=" + System.getenv("API_KEY");
        return  restTemplate.getForObject(url, FinnhubStockDto.class);
    }
}
