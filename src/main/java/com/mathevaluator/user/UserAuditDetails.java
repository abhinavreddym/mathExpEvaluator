package com.mathevaluator.user;

import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserAuditDetails {

	private String userId;

	private AtomicInteger serviceUsageCounter = new AtomicInteger(0);

	Map<Character, AtomicInteger> opeatorToCountMap = new ConcurrentHashMap<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AtomicInteger getServiceUsageCounter() {
		return serviceUsageCounter;
	}

	public void setServiceUsageCounter(AtomicInteger serviceUsageCounter) {
		this.serviceUsageCounter = serviceUsageCounter;
	}

	public void updateOperatorCount(Character c, int count) {
		opeatorToCountMap.put(c,
				new AtomicInteger(opeatorToCountMap.getOrDefault(c, new AtomicInteger(0)).addAndGet(count)));
	}
	
	public Map.Entry<Character, Integer> getMostUsedOperatorDetails(){
		
		int max = 0;
		Map.Entry<Character, Integer> ret = null;
		for(Map.Entry<Character, AtomicInteger> entry : opeatorToCountMap.entrySet()) {
			
			if(entry.getValue().get() > max) {
				max = entry.getValue().get();
				ret = new AbstractMap.SimpleEntry<Character, Integer>(entry.getKey(), max);
				
			}
		}
		
		return ret;
	}
}
