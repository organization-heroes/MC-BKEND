package com.cts.mcbkend.documentationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@ComponentScan({
    "com.cts.mcbkend.documentationapp"
    })
public class DocumentationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentationAppApplication.class, args);
    }

}
