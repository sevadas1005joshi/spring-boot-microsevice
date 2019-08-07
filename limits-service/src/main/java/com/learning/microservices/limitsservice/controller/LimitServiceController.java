package com.learning.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.microservices.limitsservice.bean.LimitConfig;
import com.learning.microservices.limitsservice.bean.Limits;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitServiceController {
	@Autowired
	private LimitConfig limitConfig;
	
	@GetMapping("/limits")
	public Limits getLimits(){		
		return new Limits(limitConfig.getMin(), limitConfig.getMax());	
	}
	
	@GetMapping("/fallback")
	@HystrixCommand(fallbackMethod="getLimitsFallback")
	public Limits getLimitsActual(){		
		throw new RuntimeException("fallback exception");	
	}
	
	public Limits getLimitsFallback(){		
		return new Limits(10, 20);	
	}
	
}
