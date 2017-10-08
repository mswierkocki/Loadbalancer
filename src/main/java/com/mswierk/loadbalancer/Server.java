package com.mswierk.loadbalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot server
 * @author Marcin Świerkocki
 *
 */

@SpringBootApplication
public class Server {
	
	private static Server instance = null;
	private boolean isRunning = false;

	public static Server getServer() {
		if(instance == null)
			instance = new Server(); 
		return instance;
	}

	public Server run() {
		if(!isRunning) {
			
			System.setProperty("server.host", "localhost");
			System.setProperty("server.port", "8888");
			System.setProperty("spring.main.banner-mode", "false");
			
			SpringApplication.run(Server.class);
			isRunning = true;
		}
		return this;

	}

}