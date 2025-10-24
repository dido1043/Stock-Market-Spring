package com.example.stockmarketspringapi.controller;

import com.example.stockmarketspringapi.model.dto.CompanyDto;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<CompanyDto> addCompany(CompanyDto companyDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.postCompany(companyDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, CompanyDto companyDto) {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.editCompany(id, companyDto));
    }
}
