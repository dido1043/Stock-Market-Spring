package com.example.stockmarketspringapi.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockResponseDto {

    private Long companyId;
    private BigDecimal marketCapitalization;
    private BigDecimal marketCapitalizationCny;
    private BigDecimal marketCapitalizationUsd;
    private BigDecimal marketCapitalizationEur;
    private BigDecimal shareOutstanding;
    private BigDecimal shareOutstandingCny;
    private BigDecimal shareOutstandingUsd;
    private BigDecimal shareOutstandingEur;
    private LocalDateTime createdAt;


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

    public BigDecimal getMarketCapitalizationCny() {
        return marketCapitalizationCny;
    }

    public void setMarketCapitalizationCny(BigDecimal marketCapitalizationCny) {
        this.marketCapitalizationCny = marketCapitalizationCny;
    }

    public BigDecimal getMarketCapitalizationUsd() {
        return marketCapitalizationUsd;
    }

    public void setMarketCapitalizationUsd(BigDecimal marketCapitalizationUsd) {
        this.marketCapitalizationUsd = marketCapitalizationUsd;
    }

    public BigDecimal getMarketCapitalizationEur() {
        return marketCapitalizationEur;
    }

    public void setMarketCapitalizationEur(BigDecimal marketCapitalizationEur) {
        this.marketCapitalizationEur = marketCapitalizationEur;
    }

    public BigDecimal getShareOutstandingCny() {
        return shareOutstandingCny;
    }

    public void setShareOutstandingCny(BigDecimal shareOutstandingCny) {
        this.shareOutstandingCny = shareOutstandingCny;
    }

    public BigDecimal getShareOutstandingUsd() {
        return shareOutstandingUsd;
    }

    public void setShareOutstandingUsd(BigDecimal shareOutstandingUsd) {
        this.shareOutstandingUsd = shareOutstandingUsd;
    }

    public BigDecimal getShareOutstandingEur() {
        return shareOutstandingEur;
    }

    public void setShareOutstandingEur(BigDecimal shareOutstandingEur) {
        this.shareOutstandingEur = shareOutstandingEur;
    }
}
