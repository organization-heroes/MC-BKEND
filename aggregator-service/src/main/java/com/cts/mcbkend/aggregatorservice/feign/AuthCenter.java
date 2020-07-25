package com.cts.mcbkend.aggregatorservice.feign;

import java.util.Map;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.mcbkend.aggregatorservice.feign.fallback.AuthFallbackService;

@RefreshScope
@FeignClient(name = "${auth-center.name}", url = "${auth-center.url}", fallback=AuthFallbackService.class)
@RibbonClient(name="auth-center")
public interface AuthCenter {
	
	@RequestMapping(value={"/login"}, method= RequestMethod.POST)
	public ResponseEntity<Object> getUserAuthorization(Map<String, String> requestBody) throws Exception;
	

}
