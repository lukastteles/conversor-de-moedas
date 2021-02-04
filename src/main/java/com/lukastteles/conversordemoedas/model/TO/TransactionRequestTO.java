package com.lukastteles.conversordemoedas.model.TO;

import java.math.BigDecimal;

public class TransactionRequestTO {

    private Long userId;
    private String baseCurrency;
    private BigDecimal baseValue;
    private String destinationCurrency;

    public TransactionRequestTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public BigDecimal getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(BigDecimal baseValue) {
        this.baseValue = baseValue;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }
}
