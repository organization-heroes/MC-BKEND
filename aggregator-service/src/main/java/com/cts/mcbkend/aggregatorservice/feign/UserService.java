package com.cts.mcbkend.aggregatorservice.feign;

import java.util.List;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.cts.mcbkend.aggregatorservice.feign.fallback.UserFallbackService;
import com.cts.mcbkend.aggregatorservice.model.UserDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;

@RefreshScope
@FeignClient(value="user-service", fallback=UserFallbackService.class)
@RibbonClient(name="user-service")
public interface UserService {
	
	@GetMapping(path = "/user/v1.0/get-user-list", produces = {"application/json, application/xml"})
	public ResponseEvent<List<UserDto>> getUserList(@RequestHeader(value = "AUTH_HEADER", required = true) String authorizationHeader) throws Exception;
	
	@PostMapping(path = "/user/v1.2/register", produces = {"application/json, application/xml"})
	public ResponseEvent<UserDto> registerUser(@RequestHeader(value = "AUTH_HEADER", required = true) String authorizationHeader, @RequestBody UserDto userDto) throws Exception;
	
	@DeleteMapping(path = "/user/v1.2/delete-user/userId/{userId}", produces = {"application/json, application/xml"})
	public ResponseEvent<String> deleteUser(@RequestHeader(value = "AUTH_HEADER", required = true) String authorizationHeader, @PathVariable("userId") Long userId, @RequestBody UserDto userDto) throws Exception;
	
	@PostMapping(path = "/user/v1.1/login", produces = {"application/json, application/xml"})
	public ResponseEvent<UserDto> loginUser(@RequestBody UserDto userDto) throws Exception;

}
