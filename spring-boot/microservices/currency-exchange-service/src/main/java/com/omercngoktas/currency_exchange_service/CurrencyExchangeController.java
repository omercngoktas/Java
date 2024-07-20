package com.omercngoktas.currency_exchange_service;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final Environment environment;

    public CurrencyExchangeController(final Environment environment, final CurrencyExchangeRepository currencyExchangeRepository) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
        @PathVariable String from,
        @PathVariable String to
        ) {
            CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);

            if(currencyExchange == null) {
                throw new RuntimeException(
                    "Unable to find data from " + from + " to " + to
                );
            }

            String port = environment.getProperty("local.server.port");
            currencyExchange.setEnvironment(port);
            
            return currencyExchange;
        }
}
