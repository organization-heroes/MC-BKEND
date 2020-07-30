package com.cts.mcbkend.aggregatorservice.feign;

import java.util.Map;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import com.cts.mcbkend.aggregatorservice.feign.fallback.AuthFallbackService;

@RefreshScope
@FeignClient(name = "${auth-center.name}", url = "${auth-center.url}", fallback=AuthFallbackService.class)
@RibbonClient(name="auth-center")
public interface AuthCenter {
	
	@PostMapping(path = "/login")
	public ResponseEntity<Object> getUserAuthorization(Map<String, String> requestBody) throws Exception;
	

}
