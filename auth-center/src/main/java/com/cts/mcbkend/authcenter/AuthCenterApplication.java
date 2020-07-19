package com.cts.mcbkend.authcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({
    "com.cts.mcbkend.authcenter"
    })
public class AuthCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthCenterApplication.class, args);
	}

}
