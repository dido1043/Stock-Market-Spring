package com.example.stockmarketspringapi.model.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockDto {
    @NotNull(message = "Company ID cannot be null")
    private Long companyId;

    @NotNull(message = "Market capitalization cannot be null")
    private BigDecimal marketCapitalization;
    private BigDecimal marketCapEur;
    private BigDecimal marketCapUsd;
    private BigDecimal marketCapCny;

    @NotNull(message = "Share outstanding cannot be null")
    private BigDecimal shareOutstanding;
    private BigDecimal shareOutstandingEur;
    private BigDecimal shareOutstandingUsd;
    private BigDecimal shareOutstandingCny;
    private LocalDateTime createdAt;


    private String name;
    private String country;
    private String symbol;
    private String website;
    private String email;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(BigDecimal marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public BigDecimal getShareOutstanding() {
        return shareOutstanding;
    }

    public void setShareOutstanding(BigDecimal shareOutstanding) {
        this.shareOutstanding = shareOutstanding;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getMarketCapEur() {
        return marketCapEur;
    }

    public void setMarketCapEur(BigDecimal marketCapEur) {
        this.marketCapEur = marketCapEur;
    }

    public BigDecimal getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(BigDecimal marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public BigDecimal getMarketCapCny() {
        return marketCapCny;
    }

    public void setMarketCapCny(BigDecimal marketCapCny) {
        this.marketCapCny = marketCapCny;
    }

    public BigDecimal getShareOutstandingEur() {
        return shareOutstandingEur;
    }

    public void setShareOutstandingEur(BigDecimal shareOutstandingEur) {
        this.shareOutstandingEur = shareOutstandingEur;
    }

    public BigDecimal getShareOutstandingUsd() {
        return shareOutstandingUsd;
    }

    public void setShareOutstandingUsd(BigDecimal shareOutstandingUsd) {
        this.shareOutstandingUsd = shareOutstandingUsd;
    }

    public BigDecimal getShareOutstandingCny() {
        return shareOutstandingCny;
    }

    public void setShareOutstandingCny(BigDecimal shareOutstandingCny) {
        this.shareOutstandingCny = shareOutstandingCny;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
