package com.github.hellocore.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User(@JsonProperty("userId") String userId) {
		super();
		this.userId = userId;
	}
	
}
