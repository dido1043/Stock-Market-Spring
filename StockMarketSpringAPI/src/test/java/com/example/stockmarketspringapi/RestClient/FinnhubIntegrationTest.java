package com.example.stockmarketspringapi.RestClient;

import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FinnhubIntegrationTest {
    private final String apiUrl = "https://finnhub.io/api/v1/stock/profile2";

    @Test
    public void getFinnhubData() {
        RestTemplate restTemplate = new RestTemplate();
        String symbol = "AAPL";
        String url = apiUrl + "?" + "symbol=" + symbol + "&" + "token=" + System.getenv("API_KEY");

        FinnhubStockDto response = restTemplate.getForObject(url, FinnhubStockDto.class);

        assertNotNull(response);
        assertNotNull(response.getMarketCapitalization());
        assertNotNull(response.getShareOutstanding());
        assertTrue(response.getMarketCapitalization() > 0);
        assertTrue(response.getShareOutstanding() > 0);
    }
}
