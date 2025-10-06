package com.example.stockmarketspringapi.controller;

import com.example.stockmarketspringapi.service.interfaces.CompanyService;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;

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

    }
//    addCompany
//    updateCompany
}
