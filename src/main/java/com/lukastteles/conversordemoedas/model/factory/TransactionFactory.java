package com.lukastteles.conversordemoedas.model.factory;

import com.lukastteles.conversordemoedas.model.TO.ExchangeRatesTO;
import com.lukastteles.conversordemoedas.model.TO.Rates;
import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.entity.CurrencyEnum;
import com.lukastteles.conversordemoedas.model.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionFactory {

    public static Transaction create(TransactionRequestTO transactionRequestTO, ExchangeRatesTO exchangeRatesTO) {
        Transaction transaction = new Transaction();
        transaction.setUserId(transactionRequestTO.getUserId());
        transaction.setBaseCurrency(CurrencyEnum.getCurrencyEnumByText(transactionRequestTO.getBaseCurrency()));
        transaction.setBaseValue(transactionRequestTO.getBaseValue());
        transaction.setDestinationCurrency(CurrencyEnum.getCurrencyEnumByText(transactionRequestTO.getDestinationCurrency()));
        transaction.setConversionTax(getConversionTax(exchangeRatesTO.getRates(), transactionRequestTO.getDestinationCurrency()));
        transaction.setDate(LocalDateTime.now());

        return transaction;
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
