package com.github.hellocore.model;

import java.io.Serializable;

public class Group implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Group(String groupId) {
		super();
		this.groupId = groupId;
	}	
	
	
}
