package com.mswierk.loadbalancer.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mswierk.loadbalancer.service.RouteService;

/**
 * Route handler for rest controller
 * @author Marcin Świerkocki
 *
 */

@RestController("RouteController")
public class RouteController {
	private final RouteService routeService;

	@Autowired
	RouteController(RouteService aRouteService) {
		routeService = aRouteService;
	}

	@RequestMapping(value = "/route", method = RequestMethod.GET)
	public String route(@RequestParam(value = "id", required = true) @NotNull final String userID) {
		return routeService.getGroup(userID);
	}
}
