package com.example.stockmarketspringapi.mappers;

import com.example.stockmarketspringapi.model.dto.CompanyDto;
import com.example.stockmarketspringapi.model.dto.StockDto;
import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.model.entity.Stock;

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
    public StockDto convertToStockDto(Stock stock){
        return new StockDto();
    }
}
