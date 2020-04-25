package com.mathevaluator.request;

import javax.xml.bind.annotation.XmlRootElement;

import com.mathevaluator.user.User;

@XmlRootElement
public class EvaluationRequest {

	private User User;

	private String exp;

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}


}
