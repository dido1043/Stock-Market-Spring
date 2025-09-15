package com.example.stockmarketspringapi.repository;

import com.example.stockmarketspringapi.model.entity.Company;
import com.example.stockmarketspringapi.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findFirstByCompanyOrderByCreatedAtDesc(Company company);
}
