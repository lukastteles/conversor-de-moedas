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

import java.util.List;
import java.util.stream.Collectors;

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

    public List<TransactionTO> getAllTransactionsByIdUser(Long idUser) throws ResourceNotFoundException {
        verifyIfUserExists(idUser);
        List<Transaction> transactionList = trasactionRepository.findAllByUserId(idUser);
        List<TransactionTO> transactionTOList = transactionList.stream().map(transaction ->
                TransactionTOFactory.create(transaction)).collect(Collectors.toList());
        return transactionTOList;
    }

    private void verifyIfUserExists(Long idUser) throws ResourceNotFoundException {
        if(!trasactionRepository.existsTransactionByUserId(idUser)){
            throw new ResourceNotFoundException("User ID not found for ID: "+idUser);
        }
    }

}
