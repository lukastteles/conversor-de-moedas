package com.lukastteles.conversordemoedas.controller;

import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.service.CurrencyConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Currency converter endpoint controller
 * @author Lukas Teles
 */
@RestController
@RequestMapping("currency-converter")
public class CurrencyConverterController {

    private final Logger logger = LoggerFactory.getLogger(CurrencyConverterController.class);

    private final CurrencyConverterService currencyConverterService;

    /**
     * Default constructor
     * @param currencyConverterService {@link com.lukastteles.conversordemoedas.service.CurrencyConverterService} service object
     */
    @Autowired
    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    /**
     * Endpoint for currency convert
     * @param transactionRequestTO {@link com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO} transaction request object
     * @return {@link org.springframework.http.ResponseEntity}
     */
    @PostMapping
    @Transactional
    public ResponseEntity<TransactionTO> convert(@Valid @RequestBody TransactionRequestTO transactionRequestTO) {
        logger.info(String.format(
                "starting convert request with TransactionRequest params: %s", transactionRequestTO.toString()));
        TransactionTO transactionTO = currencyConverterService.convert(transactionRequestTO);

        logger.info(String.format("return convert request transaction object: %s", transactionTO.toString()));
        return new ResponseEntity<>(transactionTO, HttpStatus.OK);
    }

    /**
     * Endpoint to get all transactions by ID user
     * @param idUser {@link java.lang.Long} ID user
     * @return {@link org.springframework.http.ResponseEntity}
     */
    @GetMapping(path = "/{idUser}")
    public ResponseEntity<?> getAllTransactionsByIdUser( @PathVariable("idUser") Long idUser) {
        logger.info(String.format("starting request for all transactions with idUser: %s", idUser));

        List<TransactionTO> transactionTOList = currencyConverterService.getAllTransactionsByIdUser(idUser);

        logger.info(String.format("return request for all transactions object: %s", transactionTOList.toString()));
        return new ResponseEntity<>(transactionTOList, HttpStatus.OK);
    }

}
