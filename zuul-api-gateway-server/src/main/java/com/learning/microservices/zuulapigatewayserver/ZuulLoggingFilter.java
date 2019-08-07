package com.learning.microservices.zuulapigatewayserver;

import io.micrometer.core.ipc.http.HttpSender.Request;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Object run() throws ZuulException {
		HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
		logger.info("request->{} request uri->{}",req,req.getRequestURI());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
