package com.proxtechshop.utils;

import java.io.Serializable;

public class UserRoleId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String roleKey;

	public UserRoleId(String userId, String roleKey) {
		super();
		this.userId = userId;
		this.roleKey = roleKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}	
}
