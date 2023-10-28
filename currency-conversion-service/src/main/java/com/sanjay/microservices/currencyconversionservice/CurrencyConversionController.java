package com.sanjay.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration(proxyBeanMethods = false)
class RestTemplateConfiguration {
    
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@Autowired RestTemplate restTemplate;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculate(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from",from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,uriVariables);
		CurrencyConversion cc = responseEntity.getBody();
		return new CurrencyConversion(cc.getId(), from, to, quantity, cc.getConversionMultiple(),quantity.multiply(cc.getConversionMultiple()),cc.getEnvironment()+" ResTemplate");
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateUsingFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		
		CurrencyConversion cc = currencyExchangeProxy.getVal(from, to);
		return new CurrencyConversion(cc.getId(), from, to, quantity, cc.getConversionMultiple(),quantity.multiply(cc.getConversionMultiple()),cc.getEnvironment()+" Feign");
	}
}
