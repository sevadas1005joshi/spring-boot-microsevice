package com.learning.microservices.currencyconversionservice.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.microservices.currencyconversionservice.bean.CorrencyConversionBean;

//@FeignClient(name="currency-exchange-service",url="localhost:8000")
//@FeignClient(name="currency-exchange-service")
@FeignClient(name="zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeProxy {
	
	//@GetMapping("currency-exchange/from/{from}/to/{to}")
	@GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CorrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
	
}
