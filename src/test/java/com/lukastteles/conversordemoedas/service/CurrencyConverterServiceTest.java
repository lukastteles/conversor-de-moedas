package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.model.entity.CurrencyEnum;
import com.lukastteles.conversordemoedas.model.entity.Transaction;
import com.lukastteles.conversordemoedas.repository.TransactionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class CurrencyConverterServiceTest {

    @Autowired
    private CurrencyConverterService currencyConverterService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void convert(){
        TransactionRequestTO transactionRequestTO = new TransactionRequestTO();
        transactionRequestTO.setUserId(2L);
        transactionRequestTO.setBaseCurrency("BRL");
        transactionRequestTO.setBaseValue(BigDecimal.valueOf(1));
        transactionRequestTO.setDestinationCurrency("USD");

        TransactionTO transactionTO = currencyConverterService.convert(transactionRequestTO);

        Assertions.assertThat(transactionTO).isNotNull();

        transactionRepository.deleteById(transactionTO.getId());
    }

    @Test
    public void getAllTransactionsByIdUser(){

        Transaction transaction = new Transaction();
        transaction.setUserId(1L);
        transaction.setBaseValue(BigDecimal.valueOf(1));
        transaction.setBaseCurrency(CurrencyEnum.BRL);
        transaction.setDestinationCurrency(CurrencyEnum.USD);
        transaction.setConversionTax(BigDecimal.valueOf(0.1836531388));
        transaction.setDate(LocalDateTime.now());

        transaction = transactionRepository.save(transaction);

        List<TransactionTO> transactionTOList = currencyConverterService.getAllTransactionsByIdUser(1L);
        Assertions.assertThat(transactionTOList.size()).isEqualTo(1);

        transactionRepository.delete(transaction);
    }
}
