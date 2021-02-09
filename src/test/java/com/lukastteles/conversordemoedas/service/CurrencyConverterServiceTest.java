package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.model.entity.CurrencyEnum;
import com.lukastteles.conversordemoedas.model.entity.Transaction;
import com.lukastteles.conversordemoedas.model.entity.User;
import com.lukastteles.conversordemoedas.repository.TransactionRepository;
import com.lukastteles.conversordemoedas.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Test
    public void convert(){
        User user = userRepository.save(new User("test"));

        TransactionRequestTO transactionRequestTO = new TransactionRequestTO();
        transactionRequestTO.setUserId(user.getId());
        transactionRequestTO.setBaseCurrency("BRL");
        transactionRequestTO.setBaseValue(BigDecimal.valueOf(1));
        transactionRequestTO.setDestinationCurrency("USD");

        TransactionTO transactionTO = currencyConverterService.convert(transactionRequestTO);

        Assertions.assertThat(transactionTO).isNotNull();

        transactionRepository.deleteById(transactionTO.getId());
        userRepository.delete(user);
    }

    @Test
    public void getAllTransactionsByIdUser(){
        User user = userRepository.save(new User("test"));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setBaseValue(BigDecimal.valueOf(1));
        transaction.setBaseCurrency(CurrencyEnum.BRL);
        transaction.setDestinationCurrency(CurrencyEnum.USD);
        transaction.setConversionTax(BigDecimal.valueOf(0.1836531388));
        transaction.setDate(LocalDateTime.now());

        transaction = transactionRepository.save(transaction);

        List<TransactionTO> transactionTOList = currencyConverterService.getAllTransactionsByIdUser(user.getId());
        Assertions.assertThat(transactionTOList.size()).isEqualTo(1);

        transactionRepository.delete(transaction);
        userRepository.delete(user);
    }
}
