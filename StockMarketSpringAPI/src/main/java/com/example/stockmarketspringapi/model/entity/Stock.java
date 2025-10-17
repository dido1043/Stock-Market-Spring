package com.example.stockmarketspringapi.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    @Column(name = "market_capitalization", precision = 20, scale = 2, nullable = false)
    private BigDecimal marketCapitalization;
    @Column(name = "market_capitalization_eur", precision = 20, scale = 2, nullable = false)
    private BigDecimal marketCapEur;
    @Column(name = "market_capitalization_usd", precision = 20, scale = 2, nullable = false)
    private BigDecimal marketCapUsd;
    @Column(name = "market_capitalization_cny", precision = 20, scale = 2, nullable = false)
    private BigDecimal marketCapCny;
    @Column(name = "share_outstanding", precision = 20, scale = 2, nullable = false)
    private BigDecimal shareOutstanding;
    @Column(name = "share_outstanding_eur", precision = 20, scale = 2, nullable = false)
    private BigDecimal shareOutstandingEur;
    @Column(name = "share_outstanding_usd", precision = 20, scale = 2, nullable = false)
    private BigDecimal shareOutstandingUsd;
    @Column(name = "share_outstanding_cny", precision = 20, scale = 2, nullable = false)
    private BigDecimal shareOutstandingCny;

    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'" , timezone = "UTC")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
}
