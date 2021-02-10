package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.model.to.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.to.TransactionTO;
import com.lukastteles.conversordemoedas.repository.TransactionRepository;
import com.lukastteles.conversordemoedas.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

@SpringBootTest
public class CurrencyConverterServiceTest {

    @Autowired
    private CurrencyConverterService currencyConverterService;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void convert(){
        //Mock
        BDDMockito.when(userRepository.existsById(1L)).thenReturn(true);

        TransactionRequestTO transactionRequestTO = new TransactionRequestTO();
        transactionRequestTO.setUserId(1L);
        transactionRequestTO.setBaseCurrency("BRL");
        transactionRequestTO.setBaseValue(BigDecimal.valueOf(1));
        transactionRequestTO.setDestinationCurrency("USD");

        TransactionTO transactionTO = currencyConverterService.convert(transactionRequestTO);

        Assertions.assertThat(transactionTO).isNotNull();

        transactionRepository.deleteById(transactionTO.getId());
    }

}
