package com.proxtechshop.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", length = 36, nullable = false)
	private String id;

	@Column(name = "username", length = 50, nullable = false)
	private String username;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@Column(name = "created_date", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "active", nullable = false, columnDefinition = "bit default 1")
	private boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_roles",
			joinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
			},
			inverseJoinColumns = {
					@JoinColumn(name = "role_key", referencedColumnName = "role_key", nullable = false, updatable = false)
			}
	)
	private Set<Role> roles = new HashSet<>();
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
	private Customer customer;
	
	public User() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", createdDate=" + createdDate
				+ ", active=" + active + ", roles=" + roles + ", customer=" + customer + "]";
	}
	
}
