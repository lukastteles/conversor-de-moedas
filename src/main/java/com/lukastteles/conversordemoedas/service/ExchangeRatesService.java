package com.lukastteles.conversordemoedas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukastteles.conversordemoedas.error.ResourceNotFoundException;
import com.lukastteles.conversordemoedas.model.TO.ExchangeRatesTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRatesService {

    public ExchangeRatesTO getExchangeRatesTO(String baseCurrency, String destinationCurrency) throws ResourceNotFoundException {
        String uri = String.format("https://api.exchangeratesapi.io/latest?base=%s&symbols=%s", baseCurrency, destinationCurrency);
        RestTemplate restTemplate = new RestTemplate();
        Object result = null;
        try{
            result = restTemplate.getForObject(uri, Object.class);
        }catch (RestClientException e){
            throw new ResourceNotFoundException("Exchange rates API not responding");
        }
        ObjectMapper mapper = new ObjectMapper();
        ExchangeRatesTO exchangeRatesTO = mapper.convertValue(result, ExchangeRatesTO.class);
        return exchangeRatesTO;
    }
}
