package com.cts.mcbkend.h2dbinitializer;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.h2.tools.Server;

@SpringBootApplication
@EnableDiscoveryClient
public class H2dbInitializerApplication {
	
	@Value("${custom.h2.tcpport}")
	private String tcpPort;

	public static void main(String[] args) {
		SpringApplication.run(H2dbInitializerApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", tcpPort);
    }
}
