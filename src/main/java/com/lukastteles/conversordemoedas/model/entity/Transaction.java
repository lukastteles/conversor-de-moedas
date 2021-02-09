package com.lukastteles.conversordemoedas.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private CurrencyEnum baseCurrency;

    @Column(nullable = false)
    private BigDecimal baseValue;

    @Column(nullable = false)
    private CurrencyEnum destinationCurrency;

    @Column(nullable = false)
    private BigDecimal conversionTax;

    @Column(nullable = false)
    private LocalDateTime date;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user=" + user +
                ", baseCurrency=" + baseCurrency +
                ", baseValue=" + baseValue +
                ", destinationCurrency=" + destinationCurrency +
                ", conversionTax=" + conversionTax +
                ", date=" + date +
                '}';
    }
}
