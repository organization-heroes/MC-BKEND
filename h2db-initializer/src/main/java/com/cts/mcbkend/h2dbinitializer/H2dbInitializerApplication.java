package com.cts.mcbkend.h2dbinitializer;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.h2.tools.Server;

@SpringBootApplication
public class H2dbInitializerApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2dbInitializerApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
    }
}
