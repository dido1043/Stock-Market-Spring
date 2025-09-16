package com.example.stockmarketspringapi.mappers;

import com.example.stockmarketspringapi.model.dto.CompanyDto;
import com.example.stockmarketspringapi.model.dto.FinnhubStockDto;
import com.example.stockmarketspringapi.model.dto.StockDto;
import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.model.entity.Stock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class EntityToDto {
    public CompanyDto convertToCompanyDto(Company company){
        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setCountry(company.getCountry());
        companyDto.setSymbol(company.getSymbol());
        companyDto.setWebsite(company.getWebsite());
        companyDto.setEmail(company.getEmail());
        companyDto.setCreatedAt(company.getCreatedAt());

        return companyDto;
    }
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
