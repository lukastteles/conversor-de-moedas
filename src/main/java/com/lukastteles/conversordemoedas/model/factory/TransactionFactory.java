package com.lukastteles.conversordemoedas.model.factory;

import com.lukastteles.conversordemoedas.model.TO.ExchangeRatesTO;
import com.lukastteles.conversordemoedas.model.TO.Rates;
import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.entity.CurrencyEnum;
import com.lukastteles.conversordemoedas.model.entity.Transaction;
import com.lukastteles.conversordemoedas.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Factory to create a {@link com.lukastteles.conversordemoedas.model.entity.Transaction} object
 * @author Lukas Teles
 */
public class TransactionFactory {

    /**
     * Creates a {@link com.lukastteles.conversordemoedas.model.entity.Transaction} object
     * by {@link com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO}
     * and {@link com.lukastteles.conversordemoedas.model.TO.ExchangeRatesTO}
     * @param transactionRequestTO {@link com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO} request object
     * @param exchangeRatesTO {@link com.lukastteles.conversordemoedas.model.TO.ExchangeRatesTO} exchange rates API result object
     * @return {@link com.lukastteles.conversordemoedas.model.entity.Transaction}
     */
    public static Transaction create(TransactionRequestTO transactionRequestTO, ExchangeRatesTO exchangeRatesTO) {
        Transaction transaction = new Transaction();
        transaction.setUser(new User());
        transaction.getUser().setId(transactionRequestTO.getUserId());
        transaction.setBaseCurrency(CurrencyEnum.getCurrencyEnumByText(transactionRequestTO.getBaseCurrency()));
        transaction.setBaseValue(transactionRequestTO.getBaseValue());
        transaction.setDestinationCurrency(CurrencyEnum.getCurrencyEnumByText(transactionRequestTO.getDestinationCurrency()));
        transaction.setConversionTax(getConversionTax(exchangeRatesTO.getRates(), transactionRequestTO.getDestinationCurrency()));
        transaction.setDate(LocalDateTime.now());

        return transaction;
    }

    /**
     * Get conversion tax value by currency
     * @param rates {@link com.lukastteles.conversordemoedas.model.TO.Rates} rates API result object
     * @param baseCurrency {@link java.lang.String} base currency text
     * @return {@link java.math.BigDecimal}
     */
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
