package com.proxtechshop.viewmodels;

import java.util.Arrays;

public class ProductAttributeViewModel {

	private int id;
	
	private String name;
	
	private String[] values;

	public ProductAttributeViewModel(int id, String name, String[] values) {
		super();
		this.id = id;
		this.name = name;
		this.values = values;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "ProductAttributeViewModel [id=" + id + ", name=" + name + ", values=" + Arrays.toString(values) + "]";
	}
}
