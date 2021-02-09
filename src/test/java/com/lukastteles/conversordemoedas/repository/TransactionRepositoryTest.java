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

    @Autowired
    private UserRepository userRepository;

    @Test
    public void existsTransactionByUserIdTest(){
        User user = userRepository.save(new User("test"));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setBaseValue(BigDecimal.valueOf(1));
        transaction.setBaseCurrency(CurrencyEnum.BRL);
        transaction.setDestinationCurrency(CurrencyEnum.USD);
        transaction.setConversionTax(BigDecimal.valueOf(0.1836531388));
        transaction.setDate(LocalDateTime.now());

        transaction = transactionRepository.save(transaction);

        Assertions.assertThat(transactionRepository.existsTransactionByUserId(user.getId())).isTrue();
        Assertions.assertThat(transactionRepository.existsTransactionByUserId(999L)).isFalse();

        transactionRepository.delete(transaction);
        userRepository.delete(user);

    }

    @Test
    public void findAllByUserIdTest(){
        User user = userRepository.save(new User("test"));

        Transaction transaction2 = new Transaction();
        transaction2.setUser(user);
        transaction2.setBaseValue(BigDecimal.valueOf(1));
        transaction2.setBaseCurrency(CurrencyEnum.BRL);
        transaction2.setDestinationCurrency(CurrencyEnum.USD);
        transaction2.setConversionTax(BigDecimal.valueOf(0.1836531388));
        transaction2.setDate(LocalDateTime.now());

        transaction2 = transactionRepository.save(transaction2);

        Assertions.assertThat(transactionRepository.findAllByUserId(user.getId()).size()).isEqualTo(1);

        transactionRepository.delete(transaction2);
        userRepository.delete(user);
    }
}
