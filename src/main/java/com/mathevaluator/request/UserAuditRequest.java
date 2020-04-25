package com.mathevaluator.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserAuditRequest {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
