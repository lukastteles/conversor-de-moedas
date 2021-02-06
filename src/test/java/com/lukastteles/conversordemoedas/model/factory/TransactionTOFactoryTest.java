package com.lukastteles.conversordemoedas.model.factory;

import com.lukastteles.conversordemoedas.model.TO.TransactionTO;
import com.lukastteles.conversordemoedas.model.entity.CurrencyEnum;
import com.lukastteles.conversordemoedas.model.entity.Transaction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class TransactionTOFactoryTest {

    @Test
    public void create(){

        Transaction transaction = new Transaction();
        transaction.setUserId(1L);
        transaction.setBaseValue(BigDecimal.valueOf(2));
        transaction.setBaseCurrency(CurrencyEnum.BRL);
        transaction.setDestinationCurrency(CurrencyEnum.USD);
        transaction.setConversionTax(BigDecimal.valueOf(0.5));
        transaction.setDate(LocalDateTime.now());
        TransactionTO transactionTO = TransactionTOFactory.create(transaction);

        Assertions.assertThat(transactionTO.getDestinationValue()).isEqualTo(BigDecimal.valueOf(1).setScale(2));
    }

}
