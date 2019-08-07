package com.learning.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.microservices.currencyexchangeservice.bean.ExchangeValue;
import com.learning.microservices.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment env;
	@Autowired
	private ExchangeValueRepository repo;
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
		 ExchangeValue exchangeValue = repo.findByFromAndTo(from, to);
		 logger.info("{}",exchangeValue);
		 exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		 return exchangeValue;
	}

}
