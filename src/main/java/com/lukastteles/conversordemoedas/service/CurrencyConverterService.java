package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.error.ResourceNotFoundException;
import com.lukastteles.conversordemoedas.model.TO.ExchangeRatesTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.model.entity.Transaction;
import com.lukastteles.conversordemoedas.model.factory.TransactionFactory;
import com.lukastteles.conversordemoedas.model.factory.TransactionTOFactory;
import com.lukastteles.conversordemoedas.repository.TrasactionRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {

    private final ExchangeRatesService exchangeRatesService;
    private final TrasactionRepository trasactionRepository;

    public CurrencyConverterService(ExchangeRatesService exchangeRatesService,
                                    TrasactionRepository trasactionRepository) {
        this.exchangeRatesService = exchangeRatesService;
        this.trasactionRepository = trasactionRepository;
    }

    public TransactionTO convert(TransactionRequestTO transactionRequestTO) throws ResourceNotFoundException {
        //call rest template service for 'exchangerates.io'
        ExchangeRatesTO exchangeRatesTO = exchangeRatesService.getExchangeRatesTO(
                transactionRequestTO.getBaseCurrency(),
                transactionRequestTO.getDestinationCurrency());

        //save trasaction data
        Transaction transaction =  TransactionFactory.create(transactionRequestTO, exchangeRatesTO);
        trasactionRepository.save(transaction);

        //change exchangeRatesTO object to TransactionTO
        TransactionTO transactionTO = TransactionTOFactory.create(transaction);

        return transactionTO;
    }

}
