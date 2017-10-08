package com.mswierk.loadbalancer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//
//
//@ConfigurationProperties(prefix="my")
@Component
@Configuration
@PropertySource("classpath:config.properties")
@ConfigurationProperties()
public class RouteProperties {
	public static final int METHOD_OWN = 1;
	public static final int METHOD_EXTERN = 2;
	public static final int METHOD_STRING = 3;
	
	public RouteProperties() {}

	private String hash_method;
	private List<Group> group = new ArrayList<Group>();
    public String getHash_method() {
        return hash_method;
    }

    public void setHash_method(String hash_method) {
        this.hash_method = hash_method;
    }
    

    public List<Group> getGroup() {
        return this.group;
    }
    public static class Group {
        private String name;
        private String weigth;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getWeigth() {
			return weigth;
		}
		public void setWeigth(String weigth) {
			this.weigth = weigth;
		}
        
    }
    public String[] getDistribution() {
		String[] ret = new String[100];
		int weightSum = 0;
		for (Group g : this.getGroup()) {
			String name = g.getName();
			int weight = Integer.valueOf(g.getWeigth());
			if (weight <= 0) {
				throw new IllegalArgumentException("Weight cannot be less or equal to 0");
			}
			for (int i = 0; i < weight; i++) {
				ret[i + weightSum] = name;
			}
			weightSum += weight;
		}

		if (weightSum != 100) {
			throw new IllegalArgumentException("Weight does not add up to 100");
		}
		return ret;
	}
    public int getMethod() {
    	int method = 0;
		switch(getHash_method()){
			case "e":
			case "E":
			case "Extern":
				method = METHOD_EXTERN;
				break;
			case "o":
			case "O":
			case "Own":
				method = METHOD_OWN;
				break;
			case "None":
			case "Null":
			default:
				method =  METHOD_STRING;
				break;
							
		}
		if(method == 0)
			throw new IllegalArgumentException();
		return method;
    }
    

    
}