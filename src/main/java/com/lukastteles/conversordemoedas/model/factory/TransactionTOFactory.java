package com.lukastteles.conversordemoedas.model.factory;

import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.model.entity.Transaction;

import java.math.BigDecimal;

public class TransactionTOFactory {
        public static TransactionTO create(Transaction transaction){
            TransactionTO transactionTO = new TransactionTO();
            transactionTO.setId(transaction.getId());
            transactionTO.setUserId(transaction.getUserId());
            transactionTO.setBaseCurrency(transaction.getBaseCurrency().getText());
            transactionTO.setBaseValue(transaction.getBaseValue());
            transactionTO.setDestinationCurrency(transaction.getDestinationCurrency().getText());
            transactionTO.setDestinationValue(getDestinationValue(transaction.getBaseValue(), transaction.getConversionTax()));
            transactionTO.setConversionTax(transaction.getConversionTax());
            transactionTO.setDate(transaction.getDate().toString());

        return transactionTO;
    }

    private static BigDecimal getDestinationValue(BigDecimal baseValue, BigDecimal conversionTax) {
        return baseValue.multiply(conversionTax).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
