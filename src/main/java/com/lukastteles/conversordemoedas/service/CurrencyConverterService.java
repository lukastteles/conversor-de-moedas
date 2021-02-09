package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.error.ResourceNotFoundException;
import com.lukastteles.conversordemoedas.model.TO.ExchangeRatesTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.model.entity.Transaction;
import com.lukastteles.conversordemoedas.model.factory.TransactionFactory;
import com.lukastteles.conversordemoedas.model.factory.TransactionTOFactory;
import com.lukastteles.conversordemoedas.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for management of transaction request object and to access repository
 * @author Lukas Teles
 */
@Service
public class CurrencyConverterService {

    Logger logger = LoggerFactory.getLogger(CurrencyConverterService.class);

    private final ExchangeRatesService exchangeRatesService;
    private final TransactionRepository transactionRepository;

    /**
     * Default constructor
     * @param exchangeRatesService {@link com.lukastteles.conversordemoedas.service.ExchangeRatesService} exchange rates service object
     * @param transactionRepository {@link com.lukastteles.conversordemoedas.repository.TransactionRepository} transaction repository object
     */
    public CurrencyConverterService(ExchangeRatesService exchangeRatesService,
                                    TransactionRepository transactionRepository) {
        this.exchangeRatesService = exchangeRatesService;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Get {@link com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO} object,
     * call Exchange Rates API,
     * save {@link com.lukastteles.conversordemoedas.model.entity.Transaction} data,
     * and build the result data
     * @param transactionRequestTO {@link com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO} data from request
     * @return {@link com.lukastteles.conversordemoedas.model.TO.TransactionTO}
     * @throws ResourceNotFoundException {@link com.lukastteles.conversordemoedas.error.ResourceNotFoundException}
     */
    public TransactionTO convert(TransactionRequestTO transactionRequestTO) throws ResourceNotFoundException {
        //call rest template service for 'exchangerates.io'
        ExchangeRatesTO exchangeRatesTO = exchangeRatesService.getExchangeRatesTO(
                transactionRequestTO.getBaseCurrency(),
                transactionRequestTO.getDestinationCurrency());

        //save transaction data
        Transaction transaction =  TransactionFactory.create(transactionRequestTO, exchangeRatesTO);
        transactionRepository.save(transaction);
        logger.info(String.format("transaction saved: %s", transaction));

        //change exchangeRatesTO object to TransactionTO
        TransactionTO transactionTO = TransactionTOFactory.create(transaction);

        return transactionTO;
    }

    /**
     * Get all transactions by IdUser
     * @param idUser id number for user
     * @return {@link java.util.List}
     * @throws ResourceNotFoundException {@link com.lukastteles.conversordemoedas.error.ResourceNotFoundException}
     */
    public List<TransactionTO> getAllTransactionsByIdUser(Long idUser) throws ResourceNotFoundException {
        verifyIfUserExists(idUser);
        List<Transaction> transactionList = transactionRepository.findAllByUserId(idUser);
        logger.info(String.format("transaction list for id: %s - %s", idUser, transactionList));
        List<TransactionTO> transactionTOList = transactionList.stream().map(transaction ->
                TransactionTOFactory.create(transaction)).collect(Collectors.toList());
        return transactionTOList;
    }

    /**
     * Verify if user exists by id number
     * @param idUser id number for user
     * @throws ResourceNotFoundException {@link com.lukastteles.conversordemoedas.error.ResourceNotFoundException}
     */
    private void verifyIfUserExists(Long idUser) throws ResourceNotFoundException {
        if(!transactionRepository.existsTransactionByUserId(idUser)){
            logger.warn(String.format("user with id: %s not exists", idUser));
            throw new ResourceNotFoundException("User ID not found for ID: "+idUser);
        }
        logger.info(String.format("user with id: %s exists", idUser));
    }

}
