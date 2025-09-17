package com.example.stockmarketspringapi.config.DbConfig;

import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.repository.CompanyRepository;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CompanyConfig implements CommandLineRunner {


    private final CompanyRepository companyRepository;


    public CompanyConfig(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (companyRepository.count() > 0) {
            System.out.println("Companies already seeded.");
            return;
        }

        Company company = new Company();
        company.setName("Alphabet Inc.");
        company.setCountry("US");
        company.setSymbol("GOOGL");
        company.setEmail("investors@abc.xyz");
        company.setWebsite("https://www.abc.xyz");
        company.setCreatedAt(LocalDateTime.now());
        companyRepository.save(company);
    }
}
