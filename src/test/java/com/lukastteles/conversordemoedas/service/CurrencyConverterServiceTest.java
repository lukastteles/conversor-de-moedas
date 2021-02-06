package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class CurrencyConverterServiceTest {

    @Autowired
    private CurrencyConverterService currencyConverterService;

    @Test
    public void convert(){
        TransactionRequestTO transactionRequestTO = new TransactionRequestTO();
        transactionRequestTO.setUserId(2L);
        transactionRequestTO.setBaseCurrency("BRL");
        transactionRequestTO.setBaseValue(BigDecimal.valueOf(1));
        transactionRequestTO.setDestinationCurrency("USD");

        TransactionTO transactionTO = currencyConverterService.convert(transactionRequestTO);

        Assertions.assertThat(transactionTO).isNotNull();
    }

    @Test
    public void getAllTransactionsByIdUser(){
        List<TransactionTO> transactionTOList = currencyConverterService.getAllTransactionsByIdUser(1L);
        Assertions.assertThat(transactionTOList.size()).isEqualTo(2);
    }
}
