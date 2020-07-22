package com.cts.mcbkend.aggregatorservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.mcbkend.aggregatorservice.feign.fallback.DocumentFallbackService;
import com.cts.mcbkend.aggregatorservice.feign.fallback.UserFallbackService;
import com.cts.mcbkend.aggregatorservice.model.DocumentDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;

@FeignClient(value="document-service", fallback=DocumentFallbackService.class)
public interface DocumentService {
	
	@RequestMapping(value={"/document/v1.0/get-multiple-documents"}, method= RequestMethod.GET, produces = {"application/json, application/xml"})
	public ResponseEvent<List<DocumentDto>> getMultipleDocuments(@RequestHeader(value = "AUTH_HEADER", required = true) String authorizationHeader, @RequestParam List<Long> userIds) throws Exception;
	
}
