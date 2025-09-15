package com.example.stockmarketspringapi.controller;

import com.example.stockmarketspringapi.model.dto.CompanyDto;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanies() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<?> addCompany(CompanyDto companyDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.postCompany(companyDto));
    }


}
