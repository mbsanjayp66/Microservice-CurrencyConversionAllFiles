package com.sanjay.microservice.limitservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjay.microservice.limitservices.bean.Limits;
import com.sanjay.microservice.limitservices.configuration.Configuration;

@RestController
public class LimitsController {
	
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		//return new Limits(1,1000);
		return new Limits(configuration.getMinimum(),configuration.getMaximum());
	}
}
