package com.example.stockmarketspringapi.client.interfaces;

import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;

public interface FinnhubService {

    FinnhubStockDto getApiData(String symbol);
}
