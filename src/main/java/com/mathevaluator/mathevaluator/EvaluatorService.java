package com.mathevaluator.mathevaluator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mathevaluator.chache.UserCache;
import com.mathevaluator.request.EvaluationRequest;
import com.mathevaluator.request.UserAuditRequest;
import com.mathevaluator.result.EvaluationResult;
import com.mathevaluator.result.UserAuditResult;
import com.mathevaluator.util.AuditUtil;

@Path("evaluator")
public class EvaluatorService {
	/**
	 * This method is the main service which takes User info and a mathematical expression to evaluate
	 * Returns EvaluationResult object as an xml
	 * 
	 * @param req
	 * @return
	 */
	@POST
	@Path("evaluate")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public EvaluationResult getMathExpResult(EvaluationRequest req) {
		double result = 0.0;
		String userId = req.getUser().getId();
		EvaluationResult res = new EvaluationResult();
		res.setUserId(userId);
		try {
			// Add to cache only if the user is new
			if (UserCache.getInstance().getUser(userId) == null) {
				UserCache.getInstance().addToUserCache(req.getUser());
			}

			// Running this operation in a separate thread in order not delay the response
			new Thread(() -> {
				AuditUtil.updateAuditDetails(userId, req.getExp());
			}).start();
			result = MathExpressionEvaluator.getInstance().evaluateExp(req.getExp());
			res.setResult(String.valueOf(result));
		} catch (Exception e) {
			res.setErrorMsg(e.getMessage());
		}

		return res;

	}

	/**
	 * This method returns user audit details found in @UserAuditResult
	 * @param req
	 * @return
	 */
	@POST
	@Path("useraudit")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public UserAuditResult getUserAuditDetails(UserAuditRequest req) {

		UserAuditResult result = AuditUtil.getUserAudit(req.getUserId());

		return result;
	}

}
