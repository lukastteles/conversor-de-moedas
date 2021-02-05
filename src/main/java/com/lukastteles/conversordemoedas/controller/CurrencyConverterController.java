package com.lukastteles.conversordemoedas.controller;

import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("currency-converter")
public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    @Autowired
    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @PostMapping
    public ResponseEntity<?> convert(@RequestBody TransactionRequestTO transactionRequestTO) {
        TransactionTO transactionTO = currencyConverterService.convert(transactionRequestTO);
        return new ResponseEntity<>(transactionTO, HttpStatus.OK);
    }

}
