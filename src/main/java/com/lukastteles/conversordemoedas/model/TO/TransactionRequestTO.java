package com.lukastteles.conversordemoedas.model.TO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class TransactionRequestTO {

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotEmpty(message = "Base currency is mandatory")
    private String baseCurrency;

    @Min(message = "Base value must be greater than or equal to zero", value = 0)
    private BigDecimal baseValue;

    @NotEmpty(message = "Destination currency is mandatory")
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

    @Override
    public String toString() {
        return "TransactionRequestTO{" +
                "userId=" + userId +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", baseValue=" + baseValue +
                ", destinationCurrency='" + destinationCurrency + '\'' +
                '}';
    }
}
