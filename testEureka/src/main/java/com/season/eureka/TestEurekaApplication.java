package com.season.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TestEurekaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(TestEurekaApplication.class).web(true).run(args);
	}
}
