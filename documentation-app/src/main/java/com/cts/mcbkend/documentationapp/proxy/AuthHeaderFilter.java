package com.cts.mcbkend.documentationapp.proxy;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AuthHeaderFilter extends ZuulFilter {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AuthHeaderFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		 RequestContext ctx = RequestContext.getCurrentContext();
		    HttpServletRequest request = ctx.getRequest();
		    String sessionId = null;

		    LOGGER.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		    if(request.getHeader("Authorization")!=null) {
		    	String headerValue[] = request.getHeader("Authorization").split("\\.");
		    	if(headerValue!=null && headerValue.length>0) {
		    		sessionId = headerValue[headerValue.length-1];
		    	}
		    	if(sessionId==null || StringUtils.isEmpty(sessionId)) {
		    		sessionId = UUID.randomUUID().toString();
		    	} 
		    	
		    	
		    } else {
		    		sessionId = UUID.randomUUID().toString();
		    }
		    ctx.addZuulRequestHeader("AUTH_HEADER", sessionId);
		    return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
