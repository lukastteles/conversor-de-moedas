package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.error.BadRequestException;
import com.lukastteles.conversordemoedas.model.to.ExchangeRatesTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Service class for accessing the exchange rates API
 * @author Lukas Teles
 */
@Service
public class ExchangeRatesService {

    private final Logger logger = LoggerFactory.getLogger(ExchangeRatesService.class);

    /**
     * Get exchange rates by <a href="https://api.exchangeratesapi.io/">api.exchangeratesapi.io</a>
     * @param baseCurrency {@link java.lang.String} for base currency text
     * @param destinationCurrency {@link java.lang.String} for base destination text
     * @return {@link com.lukastteles.conversordemoedas.model.to.ExchangeRatesTO}
     * @throws BadRequestException {@link BadRequestException}
     */
    public ExchangeRatesTO getExchangeRatesTO(String baseCurrency, String destinationCurrency) throws BadRequestException {
        String uri = String.format("https://api.exchangeratesapi.io/latest?base=%s&symbols=%s", baseCurrency, destinationCurrency);
        RestTemplate restTemplate = new RestTemplate();
        ExchangeRatesTO result;

        try{
            result = restTemplate.getForObject(uri, ExchangeRatesTO.class);
        }catch (RestClientException e){
            logger.error(String.format("Exchange rates API error: %s", e.getMessage()));
            throw new BadRequestException("Exchange rates API not responding");
        }

        logger.info(String.format("Exchange rates object: %s", result.toString()));
        return result;
    }
}
