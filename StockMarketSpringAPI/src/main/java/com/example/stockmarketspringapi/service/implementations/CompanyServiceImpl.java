package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.exception.NotFoundException;
import com.example.stockmarketspringapi.model.dto.CompanyDto;
import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.repository.CompanyRepository;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
  private final CompanyRepository companyRepository;
  private final ModelMapper mapper;

  @Autowired
  public CompanyServiceImpl(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
    this.mapper = new ModelMapper();
  }

  @Override
  public List<CompanyDto> getAllCompanies() {
    return companyRepository.findAll().stream().map(this::convertToCompanyDto).toList();
  }

  @Override
  public CompanyDto postCompany(CompanyDto companyDto) {
    Company company = mapper.map(companyDto, Company.class);
    company.setCreatedAt(LocalDateTime.now());
    Company savedCompany = companyRepository.save(company);
    return convertToCompanyDto(savedCompany);
  }

  @Override
  public CompanyDto editCompany(Long id, CompanyDto companyDto) {
    Company company =
        companyRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Invalid company id: " + id));

    company.setName(companyDto.getName());
    company.setCountry(companyDto.getCountry());
    company.setSymbol(companyDto.getSymbol());
    company.setWebsite(companyDto.getWebsite());
    company.setEmail(companyDto.getEmail());

    companyRepository.save(company);
    return convertToCompanyDto(company);
  }

  @Override
  public Company getCompany(Long id) {

    Company company =
        companyRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Invalid company id: " + id));
    return company;
  }

  private CompanyDto convertToCompanyDto(Company company) {
    return mapper.map(company, CompanyDto.class);
  }
}
