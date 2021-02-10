package com.lukastteles.conversordemoedas.model.factory;

import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.model.entity.Transaction;

import java.math.BigDecimal;

/**
 * Factory to create a {@link com.lukastteles.conversordemoedas.model.TO.TransactionTO} object
 * @author Lukas Teles
 */
public class TransactionTOFactory {

    /**
     * Creates a {@link com.lukastteles.conversordemoedas.model.TO.TransactionTO} object
     * by {@link com.lukastteles.conversordemoedas.model.TO.TransactionTO} object
     * @param transaction {@link com.lukastteles.conversordemoedas.model.entity.Transaction} object
     * @return {@link com.lukastteles.conversordemoedas.model.TO.TransactionTO}
     */
    public static TransactionTO create(Transaction transaction){
        TransactionTO transactionTO = new TransactionTO();
        transactionTO.setId(transaction.getId());
        transactionTO.setUserId(transaction.getUser().getId());
        transactionTO.setBaseCurrency(transaction.getBaseCurrency().getText());
        transactionTO.setBaseValue(transaction.getBaseValue());
        transactionTO.setDestinationCurrency(transaction.getDestinationCurrency().getText());
        transactionTO.setDestinationValue(getDestinationValue(transaction.getBaseValue(), transaction.getConversionTax()));
        transactionTO.setConversionTax(transaction.getConversionTax());
        transactionTO.setDate(transaction.getDate().toString());

        return transactionTO;
    }

    /**
     * Calculates destination value
     * @param baseValue {@link java.math.BigDecimal} base value
     * @param conversionTax {@link java.math.BigDecimal} conversion tax
     * @return {@link java.math.BigDecimal}
     */
    private static BigDecimal getDestinationValue(BigDecimal baseValue, BigDecimal conversionTax) {
        return baseValue.multiply(conversionTax).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
