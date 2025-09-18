package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.RestClient.interfaces.FinnhubService;
import com.example.stockmarketspringapi.exception.NotFoundException;
import com.example.stockmarketspringapi.mappers.EntityToDto;
import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;
import com.example.stockmarketspringapi.model.dto.StockDto;
import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.model.entity.Stock;
import com.example.stockmarketspringapi.repository.StockRepository;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import com.example.stockmarketspringapi.service.interfaces.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class StockServiceImpl implements StockService {
  private final CompanyService companyService;
  private final StockRepository stockRepository;
  private final EntityToDto entityToDto;
  private final FinnhubService finnhubService;

  @Autowired
  public StockServiceImpl(
      CompanyService companyService,
      StockRepository stockRepository,
      FinnhubService finnhubService) {
    this.companyService = companyService;
    this.stockRepository = stockRepository;
    this.finnhubService = finnhubService;
    this.entityToDto = new EntityToDto();
  }

  @Override
  public StockDto getStock(Long companyId) {

    Company company = companyService.getCompany(companyId);
    if (company == null) {
        throw new NotFoundException("Invalid company id: " + companyId);
    }
    String symbol = company.getSymbol();

    StockDto stockDto = null;
    Stock existing = stockRepository.findFirstByCompanyOrderByCreatedAtDesc(company);

    if (existing == null || !isSavedToday(existing)) {

      FinnhubStockDto finnhubStockDto = finnhubService.getApiData(symbol);

      stockDto = entityToDto.convertToStockDto(finnhubStockDto, company);
      saveToDb(stockDto, company);
    } else {
      stockDto = entityToDto.mapToDtoForExistingStock(existing, company);
    }

    return stockDto;
  }

  private void saveToDb(StockDto stockDto, Company company) {
    Stock stock = new Stock();

    stock.setCompany(company);
    stock.setMarketCapitalization(stockDto.getMarketCapitalization());
    stock.setShareOutstanding(stockDto.getShareOutstanding());
    stock.setCreatedAt(stockDto.getCreatedAt());

    stockRepository.save(stock);
  }

  private boolean isSavedToday(Stock stock) {
    return stock.getCreatedAt().toLocalDate().equals(LocalDateTime.now().toLocalDate());
  }
}
