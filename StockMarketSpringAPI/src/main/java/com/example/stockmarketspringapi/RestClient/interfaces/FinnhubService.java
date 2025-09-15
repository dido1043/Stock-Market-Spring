package com.example.stockmarketspringapi.RestClient.interfaces;

import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;
import org.springframework.http.ResponseEntity;

public interface FinnhubService {

    FinnhubStockDto getApiData(String symbol);
}
