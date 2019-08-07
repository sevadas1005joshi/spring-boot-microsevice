package com.learning.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learning.microservices.currencyconversionservice.bean.CorrencyConversionBean;

@RestController
public class CurrencyConversionController {
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CorrencyConversionBean converCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity){
		
		Map<String,String> uriVariables=new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
	ResponseEntity<CorrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CorrencyConversionBean.class, 
				uriVariables);
	CorrencyConversionBean correncyConversion = responseEntity.getBody();
		return new CorrencyConversionBean(correncyConversion.getId(), from, to,
				correncyConversion.getConversionMultiple(), quantity,
				quantity.multiply(correncyConversion.getConversionMultiple()), correncyConversion.getPort());
		
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CorrencyConversionBean converCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity){

	CorrencyConversionBean correncyConversion = proxy.retrieveExchangeValue(from, to);
	logger.info("{}",correncyConversion);
		return new CorrencyConversionBean(correncyConversion.getId(), from, to,
				correncyConversion.getConversionMultiple(), quantity,
				quantity.multiply(correncyConversion.getConversionMultiple()), correncyConversion.getPort());
		
	}
}
