package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.model.to.ExchangeRatesTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExchangeRatesServiceTest {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Test
    public void getExchangeRatesTOTest(){
        ExchangeRatesTO exchangeRatesTO = exchangeRatesService.getExchangeRatesTO("EUR", "USD");
        Assertions.assertThat(exchangeRatesTO).isNotNull();
        Assertions.assertThat(exchangeRatesTO.getRates().getUsd()).isPositive();
    }

}
