package com.mathevaluator.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mathevaluator.chache.UserAuditCache;
import com.mathevaluator.result.UserAuditResult;
import com.mathevaluator.user.UserAuditDetails;

/**
 * Utility class to perform user audit related tasks
 * @author abhin
 *
 */
public class AuditUtil {

	private static Set<Character> operators = Stream.of('/', '*', '+', '-').collect(Collectors.toSet());

	public static Map<Character, Integer> getOperatorCount(String exp) {

		Map<Character, Integer> operatorCountMap = new HashMap<>();
		for (int i = 0; i < exp.length(); i++) {

			char c = exp.charAt(i);

			if (operators.contains(c)) {
				operatorCountMap.put(c, operatorCountMap.getOrDefault(c, 0) + 1);
			}
		}

		return operatorCountMap;
	}

	public static UserAuditDetails updateAuditDetails(String userId, String exp) {
		
		UserAuditDetails dtls = UserAuditCache.getInstance().getUserAuditDetails(userId);
		
		//New User audit details
		if(dtls == null) {
			dtls = new UserAuditDetails();
			dtls.setUserId(userId);
			UserAuditCache.getInstance().addToUserCache(dtls);
		}
		
		dtls.getServiceUsageCounter().getAndIncrement();

		for (Entry<Character, Integer> entry : getOperatorCount(exp).entrySet()) {

			dtls.updateOperatorCount(entry.getKey(), entry.getValue());
		}
		
		return dtls;
	}
	
	public static UserAuditResult getUserAudit(String userId) {
		UserAuditResult res = new UserAuditResult();
		
		UserAuditDetails dtls = UserAuditCache.getInstance().getUserAuditDetails(userId);
		if(dtls == null) {
			res.setErrorMsg("User not found");
		}
		
		res.setUserId(dtls.getUserId());
		res.setServiceUsageFreq(dtls.getServiceUsageCounter().get());
		
		 Map.Entry<Character, Integer> entry = dtls.getMostUsedOperatorDetails();
		res.setOpratorwithMaxFreq(String.valueOf(entry.getKey()));
		res.setOperatorFreq(entry.getValue());
		
		return res;
	}
	
	
}
