package com.mathevaluator.chache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mathevaluator.user.UserAuditDetails;

/**
 * UserAuditCache to access users
 * This cache can be used along with DB
 * 
 * Will be handy to return UserAudit info when requested for
 * @author abhin
 *
 */
public class UserAuditCache {
	private UserAuditCache() {

	}
	private Map<String, UserAuditDetails> userIdToUserAuditDetailsMap = new ConcurrentHashMap<>();
	
	private static class SingletonHelper {
		private  static UserAuditCache INSTANCE = new UserAuditCache();
	}

	public static UserAuditCache getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public void addToUserCache(UserAuditDetails userAuditDetails) {
		userIdToUserAuditDetailsMap.put(userAuditDetails.getUserId(), userAuditDetails);
	}
	
	public void removeFromUserCache(UserAuditDetails userAuditDetails) {
		userIdToUserAuditDetailsMap.remove(userAuditDetails.getUserId());
	}
	
	public UserAuditDetails getUserAuditDetails(String userId) {
		if(userIdToUserAuditDetailsMap.containsKey(userId)) {
			return userIdToUserAuditDetailsMap.get(userId);
		}
		
		return null;
	}
}
