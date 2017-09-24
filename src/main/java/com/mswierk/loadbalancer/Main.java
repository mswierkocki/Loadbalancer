package com.mswierk.loadbalancer;

/**
 * Starting Spring boot server
 * @author Marcin Świerkocki
 *
 */
public class Main {

	public static void main(String[] args) {
		Server.getServer().run();
	}

}
