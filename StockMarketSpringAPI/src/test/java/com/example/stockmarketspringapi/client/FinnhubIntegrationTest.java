package com.example.stockmarketspringapi.client;

import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FinnhubIntegrationTest {
    private final String apiUrl = "https://finnhub.io/api/v1/stock/profile2";

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    FinnhubServiceImpl finnhubService = new FinnhubServiceImpl() {
        @Override
        public FinnhubStockDto getApiData(String symbol) {
            String url = apiUrl + "?symbol=" + symbol + System.getenv("API_KEY");
            return restTemplate.getForObject(url, FinnhubStockDto.class);
        }
    };


    @Test
    void getApiData_mocked() {
        FinnhubStockDto mockResponse = new FinnhubStockDto();
        mockResponse.setCountry("US");
        mockResponse.setCurrency("USD");
        mockResponse.setExchange("NASDAQ NMS - GLOBAL MARKET");
        mockResponse.setIpo("1980-12-12");
        mockResponse.setMarketCapitalization(3809379.456656);
        mockResponse.setName("Apple Inc");
        mockResponse.setPhone("14089961010");
        mockResponse.setShareOutstanding(14840.39);
        mockResponse.setTicker("AAPL");
        mockResponse.setWeburl("https://www.apple.com/");
        mockResponse.setLogo("https://static2.finnhub.io/file/publicdatany/finnhubimage/stock_logo/AAPL.png");
        mockResponse.setFinnhubIndustry("Technology");

        when(restTemplate.getForObject(anyString(), eq(FinnhubStockDto.class)))
                .thenReturn(mockResponse);


        FinnhubStockDto result = finnhubService.getApiData("AAPL");


        assertNotNull(result);
        assertEquals("Apple Inc", result.getName());
        assertEquals("AAPL", result.getTicker());
        assertEquals("US", result.getCountry());
        assertEquals("Technology", result.getFinnhubIndustry());
        assertEquals(3809379.456656, result.getMarketCapitalization());
        assertEquals("https://www.apple.com/", result.getWeburl());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(FinnhubStockDto.class));
    }
}
