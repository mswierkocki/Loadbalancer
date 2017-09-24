package com.mswierk.loadbalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot server
 * @author Marcin Świerkocki
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.mswierk.loadbalancer")
public class Server {

	public static Server getServer() {
		return new Server();
	}

	public Server run() {
		System.setProperty("server.host", "localhost");
		System.setProperty("server.port", "8888");
		SpringApplication.run(Server.class);
		return this;

	}

}