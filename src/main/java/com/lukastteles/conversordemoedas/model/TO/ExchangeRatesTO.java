package com.lukastteles.conversordemoedas.model.TO;

import java.io.Serializable;

public class ExchangeRatesTO implements Serializable {

    private Rates rates;
    private String base;
    private String date;

    public ExchangeRatesTO(Rates rates, String base, String date) {
        this.rates = rates;
        this.base = base;
        this.date = date;
    }

    public ExchangeRatesTO() {
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
