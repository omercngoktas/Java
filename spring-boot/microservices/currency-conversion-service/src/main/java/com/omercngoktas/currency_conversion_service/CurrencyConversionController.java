package com.omercngoktas.currency_conversion_service;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CurrencyConversionController {

    private final CurrencyExchangeProxy proxy;

    public CurrencyConversionController(final CurrencyExchangeProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
        @PathVariable String from,
        @PathVariable String to,
        @PathVariable BigDecimal quantity
    ) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
            "http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
            CurrencyConversion.class,
            uriVariables
        );

        CurrencyConversion currencyConversion = responseEntity.getBody();
        
        log.info("currencyConversion: " + currencyConversion);

        CurrencyConversion currencyConvertionToReturn = new CurrencyConversion(
            currencyConversion.getId(),
            currencyConversion.getFrom(),
            currencyConversion.getTo(),
            quantity,
            currencyConversion.getConversionMultiple(),
            quantity.multiply(currencyConversion.getConversionMultiple()),
            currencyConversion.getEnvironment()
        );
        log.info("currencyConvertionToReturn: " + currencyConvertionToReturn);

        return currencyConvertionToReturn;
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
        @PathVariable String from,
        @PathVariable String to,
        @PathVariable BigDecimal quantity
    ) {
        

        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
        
        log.info("currencyConversion: " + currencyConversion);

        CurrencyConversion currencyConvertionToReturn = new CurrencyConversion(
            currencyConversion.getId(),
            currencyConversion.getFrom(),
            currencyConversion.getTo(),
            quantity,
            currencyConversion.getConversionMultiple(),
            quantity.multiply(currencyConversion.getConversionMultiple()),
            currencyConversion.getEnvironment()
        );
        log.info("currencyConvertionToReturn: " + currencyConvertionToReturn);

        return currencyConvertionToReturn;
    }
}