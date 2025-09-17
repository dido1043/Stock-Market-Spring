package com.example.stockmarketspringapi.controller;

import com.example.stockmarketspringapi.model.dto.CompanyDto;
import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CompanyControllerTest {


    @Autowired
    CompanyService service;

//    @Autowired
//    TestRestTemplate restTemplate;


    @Mock
    CompanyController companyController;

//    getAllCompaniesTest

    @Test
    public void getAllCompaniesTest() throws Exception {
//        String url = "http://localhost:8080/company";
//        ResponseEntity<CompanyDto[]> response = restTemplate.getForEntity(url, CompanyDto[].class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isNotNull();
    }
//    addCompany
//    updateCompany
}
