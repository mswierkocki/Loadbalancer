package com.mswierk.loadbalancer.service;

/**
 * Interface for RouteService
 * @author Marcin Świerkocki
 *
 */
public interface RouteService {

	/**
	 * @param userID - id that is requested
	 * @return group name from distribution
	 */
	String getGroup(String userID);

}