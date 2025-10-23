package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.client.interfaces.FinnhubService;
import com.example.stockmarketspringapi.exception.errors.NotFoundException;
import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;
import com.example.stockmarketspringapi.model.dto.StockDto;
import com.example.stockmarketspringapi.model.dto.StockResponseDto;
import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.model.entity.Stock;
import com.example.stockmarketspringapi.repository.StockRepository;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceUnitTest {

    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockServiceImpl stockService;

    @Mock
    CompanyService companyService;

    @Mock
    FinnhubService finnhubService;

    private Company amd;
    private Stock existingStock;
    private FinnhubStockDto finnhubStockDto;

    @BeforeEach
    public void setUp() {
        amd = new Company();
        amd.setId(9L);
        amd.setName("Advanced Micro Devices, Inc.");
        amd.setCountry("US");
        amd.setSymbol("AMD");
        amd.setWebsite("https://www.amd.com");
        amd.setEmail("investor.relations@amd.com");

        existingStock = new Stock();
        existingStock.setId(1L);
        existingStock.setCompany(amd);
        existingStock.setMarketCapitalization(new BigDecimal("245000000000"));
        existingStock.setShareOutstanding(new BigDecimal("1620000000"));
        existingStock.setCreatedAt(LocalDateTime.now());

        finnhubStockDto = new FinnhubStockDto();
        finnhubStockDto.setMarketCapitalization(250000000000.0);
        finnhubStockDto.setShareOutstanding(1650000000.0);

    }

    @Test
    public void getStockTest_NotFoundCompany() {
        when(companyService.getCompany(999L)).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> stockService.getStock(999L));
        assertEquals("Invalid company id: 999", exception.getMessage());
        verify(companyService, times(1)).getCompany(999L);
        verify(stockRepository, never()).findFirstByCompanyOrderByCreatedAtDesc(any());
        verify(finnhubService, never()).getApiData(any());
    }

    @Test
    public void getStockTest_ExistingStock_From_Past_Date() {
        Stock oldStock = new Stock();
        oldStock.setId(1L);
        oldStock.setCompany(amd);
        oldStock.setMarketCapitalization(new BigDecimal("240000000000"));
        oldStock.setShareOutstanding(new BigDecimal("1600000000"));
        oldStock.setCreatedAt(LocalDateTime.now().minusDays(2));

        when(companyService.getCompany(9L)).thenReturn(amd);
        when(stockRepository.findFirstByCompanyOrderByCreatedAtDesc(amd)).thenReturn(oldStock);
        when(finnhubService.getApiData("AMD")).thenReturn(finnhubStockDto);
        when(stockRepository.save(any(Stock.class))).thenReturn(existingStock);

        StockResponseDto result = stockService.getStock(9L);

        assertNotNull(result);

        verify(companyService, times(1)).getCompany(9L);
        verify(stockRepository, times(1)).findFirstByCompanyOrderByCreatedAtDesc(amd);
        verify(finnhubService, times(1)).getApiData("AMD");
        verify(stockRepository, times(1)).save(any(Stock.class));
    }

    @Test
    public void getStockTest_ExistingStock_Saved_Today() {
        when(companyService.getCompany(9L)).thenReturn(amd);
        when(stockRepository.findFirstByCompanyOrderByCreatedAtDesc(amd)).thenReturn(existingStock);

        StockResponseDto result = stockService.getStock(9L);

        assertNotNull(result);
        assertEquals(9L, result.getCompanyId());
        assertEquals(new BigDecimal("245000000000"), result.getMarketCapitalization());
        assertEquals(new BigDecimal("1620000000"), result.getShareOutstanding());
        assertNotNull(result.getCreatedAt());

        verify(companyService, times(1)).getCompany(9L);
        verify(stockRepository, times(1)).findFirstByCompanyOrderByCreatedAtDesc(amd);
        verify(finnhubService, never()).getApiData(any());
        verify(stockRepository, never()).save(any(Stock.class));
    }

    @Test
    public void convertToStockDto_MapFinnhubDataCorrectly() {
        StockDto result = stockService.convertToStockDto(finnhubStockDto, amd);

        assertNotNull(result);
        assertEquals(9L, result.getCompanyId());
        assertEquals(0, new BigDecimal("250000000000.0").compareTo(result.getMarketCapitalization()));
        assertEquals(0, new BigDecimal("1650000000.0").compareTo(result.getShareOutstanding()));
        assertNotNull(result.getCreatedAt());
    }

    @Test
    public void mapToDtoForExistingStock_MapCorrectly() {
        StockDto result = stockService.mapToDtoForExistingStock(existingStock, amd);

        assertNotNull(result);
        assertEquals(9L, result.getCompanyId());
        assertEquals(0, new BigDecimal("245000000000").compareTo(result.getMarketCapitalization()));
        assertEquals(0, new BigDecimal("1620000000").compareTo(result.getShareOutstanding()));
        assertEquals(existingStock.getCreatedAt(), result.getCreatedAt());
    }
}
