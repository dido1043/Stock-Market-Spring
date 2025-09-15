package com.example.stockmarketspringapi.service.implementations;

import com.example.stockmarketspringapi.mappers.DtoToEntityMapper;
import com.example.stockmarketspringapi.mappers.EntityToDto;
import com.example.stockmarketspringapi.model.dto.CompanyDto;
import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.repository.CompanyRepository;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private EntityToDto entityToDto;
    private  DtoToEntityMapper dtoToEntityMapper;
    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        this.entityToDto = new EntityToDto();
        this.dtoToEntityMapper = new DtoToEntityMapper();
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this:: convertToCompanyDto)
                .toList();
    }

    @Override
    public CompanyDto postCompany(CompanyDto companyDto) {
        Company company = dtoToEntityMapper.companyDtoToEntity(companyDto);

        Company savedCompany = companyRepository.save(company);
        return convertToCompanyDto(savedCompany);
    }

    @Override
    public CompanyDto editCompany(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id " + id));

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

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id " + id));
        return company;
    }

    private CompanyDto convertToCompanyDto(Company company)
    {
        return entityToDto.convertToCompanyDto(company);
    }
}
