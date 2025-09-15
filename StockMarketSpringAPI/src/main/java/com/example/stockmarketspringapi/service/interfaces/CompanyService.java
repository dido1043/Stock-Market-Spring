package com.example.stockmarketspringapi.service.interfaces;

import com.example.stockmarketspringapi.model.dto.CompanyDto;
import com.example.stockmarketspringapi.model.entity.Company;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAllCompanies();

    CompanyDto postCompany(CompanyDto companyDto);

    CompanyDto editCompany(Long id, CompanyDto companyDto);

    Company getCompany(Long id);
}
