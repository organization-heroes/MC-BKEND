package com.cts.mcbkend.documentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({
    "com.cts.mcbkend.documentservice",
    })
@EnableJpaRepositories(basePackages = {
    "com.cts.mcbkend.documentservice.repository"     
   })
@EntityScan(basePackages= {
    "com.cts.mcbkend.documentservice.entity"
    })
public class DocumentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentServiceApplication.class, args);
	}

}
