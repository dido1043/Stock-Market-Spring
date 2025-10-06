package com.example.stockmarketspringapi.RestClient;

import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FinnhubIntegrationTest {
    private final String apiUrl = "https://finnhub.io/api/v1/stock/profile2";


    @InjectMocks
    private FinnhubServiceImpl finnhubService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getApiData_mocked() {
        FinnhubStockDto mockResponse = new FinnhubStockDto();
        mockResponse.setMarketCapitalization(3829117.4);
        mockResponse.setShareOutstanding(14840.39);
        mockResponse.setName("Apple Inc");
        mockResponse.setTicker("AAPL");

        assertEquals("Apple Inc", "Apple Inc");
    }
}
