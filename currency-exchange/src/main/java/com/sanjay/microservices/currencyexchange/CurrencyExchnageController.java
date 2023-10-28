package com.sanjay.microservices.currencyexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchnageController {
	private Logger logger = LoggerFactory.getLogger(CurrencyExchnageController.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getVal(@PathVariable String from,@PathVariable String to) {
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);//new CurrencyExchange(1L, "USD", "INR", 60);
		logger.info("retrieveExchangeValue called {} to {}",from,to);
		if(currencyExchange==null) {
			throw new RuntimeException("Unable to find the data for "+from+" to"+to);
		}
		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		return currencyExchange;
	}
}
