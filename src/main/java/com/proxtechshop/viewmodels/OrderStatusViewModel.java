package com.proxtechshop.viewmodels;

import java.util.HashSet;
import java.util.Set;

import com.proxtechshop.entities.Order;

public class OrderStatusViewModel {
	private String key;

	private String name;

	private String description;

	//private Set<Order> orders = new HashSet<>();
	
	
	public OrderStatusViewModel(String key, String name, String description) {
		super();
		this.key = key;
		this.name = name;
		this.description = description;
	}

	public OrderStatusViewModel() {
		super();
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

	@Override
	public String toString() {
		return "OrderStatusViewModel [key=" + key + ", name=" + name + ", description=" + description + "]";
	}

}
