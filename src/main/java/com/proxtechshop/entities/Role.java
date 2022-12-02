package com.proxtechshop.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_key", length = 20, nullable = false)
	public String roleKey;
	
	@Column(name = "role_name", length = 100, nullable = false)
	public String roleName;
	
	@Column(name = "status", nullable = false, columnDefinition = "bit default 1")
	public boolean status;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	public Set<User> users = new HashSet<>();
	
	public Role() {}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
