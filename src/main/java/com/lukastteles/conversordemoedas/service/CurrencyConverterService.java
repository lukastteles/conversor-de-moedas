package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.error.ResourceNotFoundException;
import com.lukastteles.conversordemoedas.model.TO.ExchangeRatesTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.model.entity.Transaction;
import com.lukastteles.conversordemoedas.model.factory.TransactionFactory;
import com.lukastteles.conversordemoedas.model.factory.TransactionTOFactory;
import com.lukastteles.conversordemoedas.repository.TrasactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyConverterService {

    Logger logger = LoggerFactory.getLogger(CurrencyConverterService.class);

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
        logger.info(String.format("transaction saved: %s", transaction));

        //change exchangeRatesTO object to TransactionTO
        TransactionTO transactionTO = TransactionTOFactory.create(transaction);

        return transactionTO;
    }

    public List<TransactionTO> getAllTransactionsByIdUser(Long idUser) throws ResourceNotFoundException {
        verifyIfUserExists(idUser);
        List<Transaction> transactionList = trasactionRepository.findAllByUserId(idUser);
        logger.info(String.format("transaction list for id: %s - %s", idUser, transactionList));
        List<TransactionTO> transactionTOList = transactionList.stream().map(transaction ->
                TransactionTOFactory.create(transaction)).collect(Collectors.toList());
        return transactionTOList;
    }

    private void verifyIfUserExists(Long idUser) throws ResourceNotFoundException {
        if(!trasactionRepository.existsTransactionByUserId(idUser)){
            logger.warn(String.format("user with id: %s not exists", idUser));
            throw new ResourceNotFoundException("User ID not found for ID: "+idUser);
        }
        logger.info(String.format("user with id: %s exists", idUser));
    }

}
