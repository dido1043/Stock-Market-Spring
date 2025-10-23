package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.client.interfaces.FinnhubService;
import com.example.stockmarketspringapi.exception.errors.NotFoundException;
import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;
import com.example.stockmarketspringapi.model.dto.StockDto;
import com.example.stockmarketspringapi.model.dto.StockResponseDto;
import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.model.entity.Stock;
import com.example.stockmarketspringapi.repository.StockRepository;
import com.example.stockmarketspringapi.service.interfaces.CalculationService;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import com.example.stockmarketspringapi.service.interfaces.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class StockServiceImpl implements StockService {
  private final CompanyService companyService;
  private final StockRepository stockRepository;
  private final FinnhubService finnhubService;
  private final CalculationService calculationService;

  private final ModelMapper mapper;

  @Autowired
  public StockServiceImpl(
      CompanyService companyService,
      StockRepository stockRepository,
      FinnhubService finnhubService,
      CalculationService calculationService) {
    this.companyService = companyService;
    this.stockRepository = stockRepository;
    this.finnhubService = finnhubService;
    this.calculationService = calculationService;

    this.mapper = new ModelMapper();
  }

  @Override
  public StockResponseDto getStock(Long companyId) {

    Company company = companyService.getCompany(companyId);
    if (company == null) {
      throw new NotFoundException("Invalid company id: " + companyId);
    }
    String symbol = company.getSymbol();

    StockDto stockDto = null;
    Stock existing = stockRepository.findFirstByCompanyOrderByCreatedAtDesc(company);

    if (existing == null || !isSavedToday(existing)) {

      FinnhubStockDto finnhubStockDto = finnhubService.getApiData(symbol);

      stockDto = convertToStockDto(finnhubStockDto, company);

      saveToDb(stockDto, company);
    } else {
      stockDto = mapToDtoForExistingStock(existing, company);
    }

    StockResponseDto stockResponseDto = mapper.map(stockDto, StockResponseDto.class);
    stockResponseDto.setMarketCapitalizationEur(existing.getMarketCapEur());
    stockResponseDto.setMarketCapitalizationUsd(existing.getMarketCapUsd());
    stockResponseDto.setMarketCapitalizationCny(existing.getMarketCapCny());
    stockResponseDto.setShareOutstandingEur(existing.getShareOutstandingEur());
    stockResponseDto.setShareOutstandingUsd(existing.getShareOutstandingUsd());
    stockResponseDto.setShareOutstandingCny(existing.getShareOutstandingCny());
    return stockResponseDto;
  }

  private void saveToDb(StockDto stockDto, Company company) {
    Stock stock = new Stock();

    stock.setCompany(company);
    stock.setMarketCapitalization(stockDto.getMarketCapitalization());
    stock.setMarketCapEur(new BigDecimal(calculationService.CurrencyCalculate(stockDto.getMarketCapitalization(), "EUR")));
    stock.setMarketCapUsd(new BigDecimal(calculationService.CurrencyCalculate(stockDto.getMarketCapitalization(), "USD")));
    stock.setMarketCapCny(new BigDecimal(calculationService.CurrencyCalculate(stockDto.getMarketCapitalization(), "CNY")));
    stock.setShareOutstanding(stockDto.getShareOutstanding());
    stock.setShareOutstandingEur(new BigDecimal(calculationService.CurrencyCalculate(stockDto.getShareOutstanding(), "EUR")));
    stock.setShareOutstandingUsd(new BigDecimal(calculationService.CurrencyCalculate(stockDto.getShareOutstanding(), "USD")));
    stock.setShareOutstandingCny(new BigDecimal(calculationService.CurrencyCalculate(stockDto.getShareOutstanding(), "CNY")));
    stock.setCreatedAt(stockDto.getCreatedAt());
    stockRepository.save(stock);
  }

  private boolean isSavedToday(Stock stock) {
    return stock.getCreatedAt().toLocalDate().equals(LocalDateTime.now().toLocalDate());
  }

  @Override
    public StockDto convertToStockDto(FinnhubStockDto stockData, Company company){
        StockDto stockDto = new StockDto();

        stockDto.setName(company.getName());
        stockDto.setCountry(company.getCountry());
        stockDto.setSymbol(company.getSymbol());
        stockDto.setWebsite(company.getWebsite());
        stockDto.setEmail(company.getEmail());
        stockDto.setCompanyId(company.getId());
        stockDto.setMarketCapitalization(new BigDecimal(stockData.getMarketCapitalization()));
        stockDto.setShareOutstanding(new BigDecimal(stockData.getShareOutstanding()));
        stockDto.setCreatedAt(LocalDateTime.now());

        return stockDto;
    }
    @Override
    public StockDto mapToDtoForExistingStock(Stock stock, Company company){
        StockDto stockDto = new StockDto();

        stockDto.setName(company.getName());
        stockDto.setCountry(company.getCountry());
        stockDto.setSymbol(company.getSymbol());
        stockDto.setWebsite(company.getWebsite());
        stockDto.setEmail(company.getEmail());
        stockDto.setCompanyId(company.getId());
        stockDto.setMarketCapitalization(stock.getMarketCapitalization());
        stockDto.setShareOutstanding(stock.getShareOutstanding());
        stockDto.setCreatedAt(stock.getCreatedAt());

        return stockDto;

    }
}
