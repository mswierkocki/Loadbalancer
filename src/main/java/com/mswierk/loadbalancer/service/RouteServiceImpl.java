package com.mswierk.loadbalancer.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.mswierk.loadbalancer.utils.CustomHash;

/**
 * Service that assign group name for user id based on string hash function
 * @author Marcin Świerkocki
 *
 */
@Service("RouteService")
public class RouteServiceImpl implements RouteService {
	private final RouteProperties properties;
	private final String[] distribution;
	private final int method;
	private HashFunction hashFunction;

	public RouteServiceImpl(RouteProperties aRouteProperties) {
		this.properties = aRouteProperties;
		distribution = aRouteProperties.getDistribution();
		this.method = properties.getMethod();
	}


	@PostConstruct
	public void setHashFunction() {
		this.hashFunction = Hashing.crc32();
			
		
    }
	/* (non-Javadoc)
	 * @see com.mswierk.loadbalancer.service.RouteServicet#getGroup(java.lang.String)
	 */
	@Override
	public String getGroup(String userID) {
		long hash =0;
		switch(method) {
		case RouteProperties.METHOD_EXTERN :
			HashCode code = hashFunction.hashString(userID, Charsets.UTF_8); 
			hash = code.padToLong();
			
		break;
		case RouteProperties.METHOD_OWN :
			hash = CustomHash.hash(userID);
		break;
		case RouteProperties.METHOD_STRING :
		default:
			hash = userID.hashCode();
		break;
		}
		if (hash < 0)
			hash *= -1;
		return distribution[(int) (hash % 100)];
		
	}

}

