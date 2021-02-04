package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.model.TO.ExchangeRatesTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.factory.TransactionTOFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {

    private final ExchangeRatesService exchangeRatesService;

    public CurrencyConverterService(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    public TransactionTO convert(TransactionRequestTO transactionRequestTO) {
        //call rest template service for 'exchangerates.io'
        ExchangeRatesTO exchangeRatesTO = exchangeRatesService.getExchangeRatesTO(transactionRequestTO.getBaseCurrency(),
                transactionRequestTO.getDestinationCurrency());

        //change exchangeRatesTO object to TransactionTO
        TransactionTO transactionTO = TransactionTOFactory.create(transactionRequestTO, exchangeRatesTO);
        return transactionTO;
    }
}
