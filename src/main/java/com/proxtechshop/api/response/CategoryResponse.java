package com.proxtechshop.api.response;

import com.proxtechshop.entities.Category;

public class CategoryResponse {

	private int id;
	
	private String image;
	
	private String name;
	
	public CategoryResponse(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.image = category.getThumbnail();
	}
	
	public CategoryResponse() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
