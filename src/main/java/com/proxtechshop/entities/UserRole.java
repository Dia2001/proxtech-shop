package com.proxtechshop.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.proxtechshop.utils.UserRoleId;

@Entity
@Table(name = "user_roles")
@IdClass(UserRoleId.class)
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", length = 36, nullable = false)
	public String userId;
	
	@Id
	@Column(name = "role_key", length = 20, nullable = false)
	public String roleKey;

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
