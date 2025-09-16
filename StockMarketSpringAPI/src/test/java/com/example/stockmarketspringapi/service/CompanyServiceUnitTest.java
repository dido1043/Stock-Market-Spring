package com.example.stockmarketspringapi.service;

import com.example.stockmarketspringapi.StockMarketSpringApiApplication;
import com.example.stockmarketspringapi.model.dto.CompanyDto;
import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.repository.CompanyRepository;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest
public class CompanyServiceUnitTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyService companyService;
    @Test
    public void editCompany() {
        Company existingCompany = new Company();
        existingCompany.setName("Old Name");
        existingCompany.setCountry("Old Country");
        existingCompany.setSymbol("OLD");
        existingCompany.setWebsite("http://old.com");
        existingCompany.setEmail("old@email.com");
        existingCompany.setCreatedAt(LocalDateTime.now());

        CompanyDto updateDto = new CompanyDto();
        updateDto.setName("Advanced Micro Devices, Inc.");
        updateDto.setCountry("US");
        updateDto.setSymbol("AMD");
        updateDto.setWebsite("https://www.amd.com");
        updateDto.setEmail("investor.relations@amd.com");

        when(companyRepository.findById(9L)).thenReturn(java.util.Optional.of(existingCompany));
        when(companyRepository.save(any(Company.class))).thenAnswer(inv -> inv.getArgument(0));

        CompanyDto result = companyService.editCompany(9L, updateDto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Advanced Micro Devices, Inc.");
        assertThat(result.getCountry()).isEqualTo("US");
        assertThat(result.getSymbol()).isEqualTo("AMD");
        assertThat(result.getWebsite()).isEqualTo("https://www.amd.com");
        assertThat(result.getEmail()).isEqualTo("investor.relations@amd.com");

        verify(companyRepository).findById(9L);
        verify(companyRepository).save(existingCompany);
        verifyNoMoreInteractions(companyRepository);

        assertThat(existingCompany.getName()).isEqualTo("Advanced Micro Devices, Inc.");
        assertThat(existingCompany.getCountry()).isEqualTo("US");
        assertThat(existingCompany.getSymbol()).isEqualTo("AMD");
        assertThat(existingCompany.getWebsite()).isEqualTo("https://www.amd.com");
        assertThat(existingCompany.getEmail()).isEqualTo("investor.relations@amd.com");

    }
    @Test
    public void getCompany() {
        Company company = new Company();
        company.setId(9L);
        company.setName("Advanced Micro Devices, Inc.");
        company.setCountry("US");
        company.setSymbol("AMD");
        company.setWebsite("https://www.amd.com");
        company.setEmail("investor.relations@amd.com");
        company.setCreatedAt(LocalDateTime.now());

        when(companyRepository.findById(9L)).thenReturn(java.util.Optional.of(company));

        Company result = companyService.getCompany(9L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Advanced Micro Devices, Inc.");
        assertThat(result.getCountry()).isEqualTo("US");
        assertThat(result.getSymbol()).isEqualTo("AMD");
        assertThat(result.getWebsite()).isEqualTo("https://www.amd.com");
        assertThat(result.getEmail()).isEqualTo("investor.relations@amd.com");
        assertThat(result.getCreatedAt())
                .isNotNull()
                .isCloseTo(LocalDateTime.now(), within(5, ChronoUnit.SECONDS));

        verify(companyRepository).findById(9L);
        verifyNoMoreInteractions(companyRepository);

    }

    @Test
    public void postCompany() {
        Company company = new Company();
        company.setId(1L);
        company.setName("Advanced Micro Devices, Inc.");
        company.setCountry("US");
        company.setSymbol("AMD");
        company.setWebsite("https://www.amd.com");
        company.setEmail("investor.relations@amd.com");
        company.setCreatedAt(LocalDateTime.now());

        when(companyRepository.findById(1L)).thenReturn(java.util.Optional.of(company));

        Company result = companyService.getCompany(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Advanced Micro Devices, Inc.");
        assertThat(result.getCountry()).isEqualTo("US");
        assertThat(result.getSymbol()).isEqualTo("AMD");
        assertThat(result.getWebsite()).isEqualTo("https://www.amd.com");
        assertThat(result.getEmail()).isEqualTo("investor.relations@amd.com");
        assertThat(result.getCreatedAt())
                .isNotNull()
                .isCloseTo(LocalDateTime.now(), within(5, ChronoUnit.SECONDS));

        verify(companyRepository).findById(9L);
        verifyNoMoreInteractions(companyRepository);
    }

    @Test
    public void getAllCompanies() {
        Company company = new Company();

        company.setName("Advanced Micro Devices, Inc.");
        company.setCountry("US");
        company.setSymbol("AMD");
        company.setWebsite("https://www.amd.com");
        company.setEmail("investor.relations@amd.com");
        company.setCreatedAt(LocalDateTime.now());

        when(companyRepository.findAll()).thenReturn(java.util.List.of(company));

        List<CompanyDto> result = companyService.getAllCompanies();

        assertThat(result).hasSize(1);
        CompanyDto dto = result.get(0);

        assertThat(dto.getName()).isEqualTo("Advanced Micro Devices, Inc.");
        assertThat(dto.getCountry()).isEqualTo("US");
        assertThat(dto.getSymbol()).isEqualTo("AMD");
        assertThat(dto.getWebsite()).isEqualTo("https://www.amd.com");
        assertThat(dto.getEmail()).isEqualTo("investor.relations@amd.com");
        assertThat(dto.getCreatedAt())
                .isNotNull()
                .isCloseTo(LocalDateTime.now(), within(5, ChronoUnit.SECONDS));

        verify(companyRepository).findAll();
        verifyNoMoreInteractions(companyRepository);
    }
}
