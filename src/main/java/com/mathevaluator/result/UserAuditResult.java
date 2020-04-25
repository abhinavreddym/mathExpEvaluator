package com.mathevaluator.result;

/**
 * This hold information of a user related to 
 * evaluation service usage count, most used  operator (and its count) in the mathematical expressions of the user
 * errorMsg will be null in successful processing and a corresponding error in case of failures
 * 
 * @author abhin
 *
 */
public class UserAuditResult {

	private int serviceUsageFreq;
	private String opratorwithMaxFreq;
	private int operatorFreq;
	private String userId;
	private String errorMsg;

	public int getServiceUsageFreq() {
		return serviceUsageFreq;
	}

	public void setServiceUsageFreq(int serviceUsageFreq) {
		this.serviceUsageFreq = serviceUsageFreq;
	}

	public String getOpratorwithMaxFreq() {
		return opratorwithMaxFreq;
	}

	public void setOpratorwithMaxFreq(String opratorwithMaxFreq) {
		this.opratorwithMaxFreq = opratorwithMaxFreq;
	}

	public int getOperatorFreq() {
		return operatorFreq;
	}

	public void setOperatorFreq(int operatorFreq) {
		this.operatorFreq = operatorFreq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
