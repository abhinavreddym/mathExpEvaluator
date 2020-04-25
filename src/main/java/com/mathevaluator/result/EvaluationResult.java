package com.mathevaluator.result;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Result of mathematical expression evaluation.
 * Contains the final output value and userId.
 * errorMsg will be null in successful processing and a corresponding error in case of failures
 * @author abhin
 *
 */
@XmlRootElement
public class EvaluationResult {
	private String result;
	private String userId;
	private String errorMsg;

	public EvaluationResult(String result) {
		this.result = result;
	}

	public EvaluationResult() {
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
