package com.cts.mcbkend.loanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({
    "com.cts.mcbkend.loanservice",
    })
@EnableJpaRepositories(basePackages = {
    "com.cts.mcbkend.loanservice.repository"     
   })
@EntityScan(basePackages= {
    "com.cts.mcbkend.loanservice.entity"
    })
public class LoanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanServiceApplication.class, args);
	}

}
