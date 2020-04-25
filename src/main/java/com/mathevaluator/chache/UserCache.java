package com.mathevaluator.chache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mathevaluator.user.User;

/**
 * User cache  to access users
 * This cache can be used along with DB
 * @author abhin
 *
 */
public class UserCache {

	private UserCache() {

	}

	private Map<String, User> userIdToUserMap = new ConcurrentHashMap<>();

	private static class SingletonHelper {
		private final static UserCache INSTANCE = new UserCache();
	}

	public static UserCache getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public void addToUserCache(User user) {
		userIdToUserMap.put(user.getId(), user);
	}

	public void removeFromUserCache(User user) {
		userIdToUserMap.remove(user.getId());
	}

	public User getUser(String userId) {

		return userIdToUserMap.get(userId);
	}
}
