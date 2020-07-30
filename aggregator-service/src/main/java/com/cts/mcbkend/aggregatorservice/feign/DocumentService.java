package com.cts.mcbkend.aggregatorservice.feign;

import java.util.List;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.mcbkend.aggregatorservice.feign.fallback.DocumentFallbackService;
import com.cts.mcbkend.aggregatorservice.model.DocumentDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;

@RefreshScope
@FeignClient(value="document-service", fallback=DocumentFallbackService.class)
public interface DocumentService {
	
	@GetMapping(path = "/document/v1.0/get-multiple-documents", produces = {"application/json, application/xml"})
	public ResponseEvent<List<DocumentDto>> getMultipleDocuments(@RequestHeader(value = "AUTH_HEADER", required = true) String authorizationHeader, @RequestParam List<Long> userIds) throws Exception;
	
}
