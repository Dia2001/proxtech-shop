package com.proxtechshop.api.response;

import com.proxtechshop.entities.Brand;

public class BrandReponse {
private int id;
	
	private String image;
	
	private String name;
	
	public BrandReponse(Brand brand) {
		this.id = brand.getId();
		this.name = brand.getName();
		this.image = brand.getThumbnail();
	}
	
	public BrandReponse() {}

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
