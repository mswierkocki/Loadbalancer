package com.mswierk.loadbalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that assign group name for user id based on string hash function
 * @author Marcin Świerkocki
 *
 */
@Service
public class RouteService {
	private final String[] distribution;

	@Autowired
	public RouteService(Config aConfig) {
		distribution = aConfig.getDistribution();
	}

	public String getGroup(String userID) {
		long hash = userID.hashCode();
		if (hash < 0)
			hash *= -1;
		return distribution[(int) (hash % 100)];
	}

}
