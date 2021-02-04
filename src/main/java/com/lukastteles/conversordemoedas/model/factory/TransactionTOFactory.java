package com.lukastteles.conversordemoedas.model.factory;

import com.lukastteles.conversordemoedas.model.TO.ExchangeRatesTO;
import com.lukastteles.conversordemoedas.model.TO.Rates;
import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionTOFactory {

    public static TransactionTO create(TransactionRequestTO transactionRequestTO, ExchangeRatesTO exchangeRatesTO) {
        TransactionTO transactionTO = new TransactionTO();
        transactionTO.setUserId(transactionRequestTO.getUserId());
        transactionTO.setBaseCurrency(transactionRequestTO.getBaseCurrency());
        transactionTO.setBaseValue(transactionRequestTO.getBaseValue());
        transactionTO.setDestinationCurrency(transactionRequestTO.getDestinationCurrency());
        transactionTO.setConversionTax(getConversionTax(exchangeRatesTO.getRates(), transactionRequestTO.getDestinationCurrency()));
        transactionTO.setDestinationValue(getDestinationValue(transactionRequestTO.getBaseValue(), transactionTO.getConversionTax()));
        transactionTO.setDate(LocalDateTime.now().toString());

        return transactionTO;
    }

    private static BigDecimal getDestinationValue(BigDecimal baseValue, BigDecimal conversionTax) {
        return baseValue.multiply(conversionTax);
    }

    private static BigDecimal getConversionTax(Rates rates, String baseCurrency) {
        switch (baseCurrency){
            case "BRL":
                return rates.getBrl();
            case "USD":
                return rates.getUsd();
            case "EUR":
                return rates.getEur();
            case "JPY":
                return rates.getJpy();
            default:
                return BigDecimal.ZERO;
        }
    }
}
