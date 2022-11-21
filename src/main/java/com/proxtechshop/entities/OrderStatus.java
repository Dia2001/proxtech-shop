package com.proxtechshop.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "order_status")
@Data
public class OrderStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_status_key", length = 30)
	private String key;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "description", length = 100, nullable = true)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderStatus")
	private Set<Order> orders = new HashSet<>();

	public OrderStatus(String key, String name, String description) {
		super();
		this.key = key;
		this.name = name;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
