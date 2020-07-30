package com.cts.mcbkend.aggregatorservice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.cts.mcbkend.aggregatorservice.feign.AuthCenter;
import com.cts.mcbkend.aggregatorservice.feign.fallback.AuthFallbackService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:bootstrap-test.yml")
class AggregatorServiceApplicationTests {

	@Test
	void contextLoads() {
		Assert.isAssignable(AuthCenter.class, AuthFallbackService.class);
	}

}
