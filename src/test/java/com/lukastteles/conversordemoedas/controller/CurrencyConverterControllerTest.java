package com.lukastteles.conversordemoedas.controller;

import com.lukastteles.conversordemoedas.model.TO.TransactionRequestTO;
import com.lukastteles.conversordemoedas.model.entity.CurrencyEnum;
import com.lukastteles.conversordemoedas.model.entity.Transaction;
import com.lukastteles.conversordemoedas.model.entity.User;
import com.lukastteles.conversordemoedas.repository.TransactionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.util.Arrays.asList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CurrencyConverterControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    public TransactionRepository transactionRepository;

    public Transaction transaction = new Transaction();

    public CurrencyConverterControllerTest(){
        transaction.setUser(new User());
        transaction.getUser().setId(1L);
        transaction.setBaseValue(BigDecimal.valueOf(1));
        transaction.setBaseCurrency(CurrencyEnum.BRL);
        transaction.setDestinationCurrency(CurrencyEnum.USD);
        transaction.setConversionTax(BigDecimal.valueOf(0.1836531388));
        transaction.setDate(LocalDateTime.now());
    }

    @Test
    public void postConvertTestShouldReturnStatusCode200(){
        //Parameters
        TransactionRequestTO transactionRequestTO = new TransactionRequestTO();
        transactionRequestTO.setDestinationCurrency("BRL");
        transactionRequestTO.setBaseCurrency("USD");
        transactionRequestTO.setBaseValue(BigDecimal.valueOf(2));
        transactionRequestTO.setUserId(1L);

        //Test with 200 OK
        ResponseEntity<String> response = testRestTemplate.postForEntity("/currency-converter", transactionRequestTO, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void postConvertTestShouldReturnStatusCode400(){
        //Parameters with currencies not supported
        TransactionRequestTO transactionRequestTO = new TransactionRequestTO();
        transactionRequestTO.setDestinationCurrency("ZZZ");
        transactionRequestTO.setBaseCurrency("AAA");
        transactionRequestTO.setBaseValue(BigDecimal.valueOf(2));
        transactionRequestTO.setUserId(1L);

        //Test with 400 BAD REQUEST
        ResponseEntity<String> response = testRestTemplate.postForEntity("/currency-converter", transactionRequestTO, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getAllTransactionsByIdUserTestShouldReturnStatusCode200(){
        //Mock
        BDDMockito.when(transactionRepository.existsTransactionByUserId(1L)).thenReturn(true);
        BDDMockito.when(transactionRepository.findAllByUserId(1L)).thenReturn(asList(transaction));

        //Test with 200 OK
        ResponseEntity<String> response = testRestTemplate.getForEntity("/currency-converter/1", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getAllTransactionsByIdUserTestShouldReturnStatusCode400(){

        //Mock
        BDDMockito.when(transactionRepository.existsTransactionByUserId(2L)).thenReturn(false);

        //Test with 400 Bad Request
        ResponseEntity<String> response = testRestTemplate.getForEntity("/currency-converter/1", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
