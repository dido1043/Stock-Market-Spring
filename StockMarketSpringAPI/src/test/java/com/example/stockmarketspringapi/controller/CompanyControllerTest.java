//package com.example.stockmarketspringapi.controller;
//
//import com.example.stockmarketspringapi.model.dto.CompanyDto;
//import com.example.stockmarketspringapi.service.interfaces.CompanyService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(controllers = CompanyController.class)
//@ExtendWith(MockitoExtension.class)
//public class CompanyControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private CompanyService companyService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private CompanyDto testCompany;
//    private List<CompanyDto> testCompanies;
//
//    @BeforeEach
//    void setUp() {
//        testCompany = new CompanyDto();
//        testCompany.setId(1L);
//        testCompany.setName("Test Company");
//        testCompany.setCountry("US");
//        testCompany.setSymbol("TEST");
//        testCompany.setWebsite("https://test.com");
//        testCompany.setEmail("test@test.com");
//        testCompany.setCreatedAt(LocalDateTime.now());
//
//        testCompanies = Arrays.asList(testCompany);
//    }
//
//    @Test
//    void getAllCompanies_ShouldReturnListOfCompanies() throws Exception {
//        when(companyService.getAllCompanies()).thenReturn(testCompanies);
//
//        mockMvc.perform(get("/company"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].name").value("Test Company"))
//                .andExpect(jsonPath("$[0].country").value("US"))
//                .andExpect(jsonPath("$[0].symbol").value("TEST"));
//    }
//
//    @Test
//    void addCompany_ShouldReturnCreatedCompany() throws Exception {
//        when(companyService.postCompany(any(CompanyDto.class))).thenReturn(testCompany);
//
//        mockMvc.perform(post("/company")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(testCompany)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Test Company"))
//                .andExpect(jsonPath("$.country").value("US"))
//                .andExpect(jsonPath("$.symbol").value("TEST"));
//    }
//
//    @Test
//    void updateCompany_ShouldReturnUpdatedCompany() throws Exception {
//        CompanyDto updatedCompany = new CompanyDto();
//        updatedCompany.setId(1L);
//        updatedCompany.setName("Updated Company");
//        updatedCompany.setCountry("US");
//        updatedCompany.setSymbol("UPDT");
//
//        when(companyService.editCompany(eq(1L), any(CompanyDto.class))).thenReturn(updatedCompany);
//
//        mockMvc.perform(put("/company/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedCompany)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Updated Company"))
//                .andExpect(jsonPath("$.symbol").value("UPDT"));
//    }
//
//    @Test
//    void addCompany_WithInvalidData_ShouldReturnBadRequest() throws Exception {
//        CompanyDto invalidCompany = new CompanyDto();
//        // Missing required fields
//
//        mockMvc.perform(post("/company")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(invalidCompany)))
//                .andExpect(status().isBadRequest());
//    }
//}
