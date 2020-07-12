package com.cts.mcbkend.documentationapp.rest.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * zuul Swagger
 * @author Sukanta
 *
 */
@RestController
@RequestMapping("/demo")
@Api(tags="zuul rest api")
public class DemoController {

	@GetMapping("/hello")
	@ApiOperation(value="demo",notes="demo")
	@ApiImplicitParam(name="name",value="Sukanta",example="Sukanta")
	public String hello(String name) {
		return "hi," + name + ",this is zuul api! ";
	}
}