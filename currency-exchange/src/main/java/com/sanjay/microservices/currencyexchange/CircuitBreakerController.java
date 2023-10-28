package com.sanjay.microservices.currencyexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api", fallbackMethod = "hardCodeResponse")
	//@CircuitBreaker(name = "default",fallbackMethod = "hardCodeResponse")
	//@RateLimiter(name = "default")
	@Bulkhead(name = "default")
	public String sampleApi() {
//		logger.info("Sample Api Received");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
//		return forEntity.getBody();
		return "Sample API";
	}
	
	public String hardCodeResponse(Exception ex) {
		return "fallback-response";
	}
	public String hardCodeResponse2(Exception ex) {
		return "rate-limiter-failure";
	}
	
}
