package com.proxtechshop.api.response;

import java.math.BigDecimal;

public class ProductOrderResponse {

	private String name;
	
	private String description;
	
	private String thumbnail;
	
	private BigDecimal price;
	
	private int number;
	
	public ProductOrderResponse() {
		super();
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
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "ProductOrderResPonse [name=" + name + ", description=" + description + ", thumbnail=" + thumbnail
				+ ", price=" + price + ", number=" + number + "]";
	}
	

}
