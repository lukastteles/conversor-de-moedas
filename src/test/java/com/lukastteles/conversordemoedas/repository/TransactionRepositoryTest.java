package com.lukastteles.conversordemoedas.repository;

import com.lukastteles.conversordemoedas.model.entity.CurrencyEnum;
import com.lukastteles.conversordemoedas.model.entity.Transaction;
import com.lukastteles.conversordemoedas.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void existsTransactionByUserIdTest(){
        Transaction transaction = new Transaction();
        transaction.setUser(new User());
        transaction.getUser().setId(1L);
        transaction.setBaseValue(BigDecimal.valueOf(1));
        transaction.setBaseCurrency(CurrencyEnum.BRL);
        transaction.setDestinationCurrency(CurrencyEnum.USD);
        transaction.setConversionTax(BigDecimal.valueOf(0.1836531388));
        transaction.setDate(LocalDateTime.now());

        transaction = transactionRepository.save(transaction);

        Assertions.assertThat(transactionRepository.existsTransactionByUserId(1L)).isTrue();
        Assertions.assertThat(transactionRepository.existsTransactionByUserId(9L)).isFalse();

        transactionRepository.delete(transaction);


    }

    @Test
    public void findAllByUserIdTest(){

        Transaction transaction2 = new Transaction();
        transaction2.setUser(new User());
        transaction2.getUser().setId(1L);
        transaction2.setBaseValue(BigDecimal.valueOf(1));
        transaction2.setBaseCurrency(CurrencyEnum.BRL);
        transaction2.setDestinationCurrency(CurrencyEnum.USD);
        transaction2.setConversionTax(BigDecimal.valueOf(0.1836531388));
        transaction2.setDate(LocalDateTime.now());

        transaction2 = transactionRepository.save(transaction2);

        Assertions.assertThat(transactionRepository.findAllByUserId(1L).size()).isEqualTo(1);

        transactionRepository.delete(transaction2);
    }
}
