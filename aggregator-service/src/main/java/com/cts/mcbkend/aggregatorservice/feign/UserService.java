package com.cts.mcbkend.aggregatorservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.mcbkend.aggregatorservice.feign.fallback.UserFallbackService;
import com.cts.mcbkend.aggregatorservice.model.UserDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;

@FeignClient(value="user-service", fallback=UserFallbackService.class)
public interface UserService {
	
	@RequestMapping(value={"/user/v1.0/get-user-list"}, method= RequestMethod.GET, produces = {"application/json, application/xml"})
	public ResponseEvent<List<UserDto>> getUserList() throws Exception;
	
}
