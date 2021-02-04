package com.lukastteles.conversordemoedas.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Trasaction {

    private Long id;
    private Long userId;
    private CurrencyEnum baseCurrency;
    private BigDecimal baseValue;
    private CurrencyEnum destinationCurrency;
    private BigDecimal conversionTax;
    private LocalDateTime date;

    public Trasaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserInd(Long userId) {
        this.userId = userId;
    }

    public CurrencyEnum getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(CurrencyEnum baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public BigDecimal getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(BigDecimal baseValue) {
        this.baseValue = baseValue;
    }

    public CurrencyEnum getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(CurrencyEnum destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }

    public BigDecimal getConversionTax() {
        return conversionTax;
    }

    public void setConversionTax(BigDecimal conversionTax) {
        this.conversionTax = conversionTax;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
