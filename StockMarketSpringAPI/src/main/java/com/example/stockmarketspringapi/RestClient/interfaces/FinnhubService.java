package com.example.stockmarketspringapi.RestClient.interfaces;

import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;

public interface FinnhubService {

    FinnhubStockDto getApiData(String symbol);
}
