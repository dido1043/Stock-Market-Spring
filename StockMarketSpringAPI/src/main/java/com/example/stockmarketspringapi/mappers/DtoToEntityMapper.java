package com.example.stockmarketspringapi.mappers;

import com.example.stockmarketspringapi.model.dto.CompanyDto;
import com.example.stockmarketspringapi.model.dto.StockDto;
import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.model.entity.Stock;

import java.time.LocalDateTime;

public class DtoToEntityMapper {
    public  Company companyDtoToEntity(CompanyDto companyDto) {
        Company company = new Company();


        company.setName(companyDto.getName());
        company.setCountry(companyDto.getCountry());
        company.setSymbol(companyDto.getSymbol());
        company.setWebsite(companyDto.getWebsite());
        company.setEmail(companyDto.getEmail());
        company.setCreatedAt(LocalDateTime.now());

        return company;
    }
    public  Stock stockDtoToEntity(StockDto stockDto) {
        return null;
    }
}
