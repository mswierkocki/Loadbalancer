package com.mswierk.loadbalancer.utils;

public class CustomHash {
	public static long hash(String str) {
		long hash = 0;
		int len = str.length();
		for(int i = 0; i<len;i++) {
			char c = str.charAt(i);
			int n = Character.getNumericValue(c);
			hash += n%2==0? 
					i%2==0? n*(32-i>>2) : n>>2*i
					:
					i%2==0	?-1*n*i>>16:n*(32-i>>8);
			
		}
		return hash;
	}
}
