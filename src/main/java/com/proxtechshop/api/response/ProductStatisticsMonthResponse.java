package com.proxtechshop.api.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.proxtechshop.entities.Product;
import com.proxtechshop.utils.Utils;

public class ProductStatisticsMonthResponse {

	private String id;
	
	private String name;
	
	private String image;
	
	private long price;
	
	private long totalPrice;
	
	private int quantityPrice;
	
	private BrandReponse brand;
	
	private List<CategoryResponse> categories;
	
	private String orderDate;
	
	private String orderUpdate;
	
	private String status;
	
	public ProductStatisticsMonthResponse() {}
	
	public ProductStatisticsMonthResponse(Product product, int quantity, Date create, Date update, String status) {
		this.id = product.getId();
		this.name = product.getName();
		this.image = product.getThumbnail();
		this.price = product.getPrice().longValue();
		this.quantityPrice = quantity;
		this.totalPrice = this.price * this.quantityPrice;
		this.brand = new BrandReponse(product.getBrand());
		this.categories = new ArrayList<>();
		product.getCategories().forEach(c -> {
			categories.add(new CategoryResponse(c));
		});
		this.orderDate = Utils.convertDateToddMMyyyy(create);
		this.orderUpdate = Utils.convertDateToddMMyyyy(update);
		this.status = status;
	}

	public ProductStatisticsMonthResponse(String id, String name, String image, long price, long totalPrice,
			int quantityPrice, BrandReponse brand, List<CategoryResponse> categories, String orderDate, String orderUpdate,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.totalPrice = totalPrice;
		this.quantityPrice = quantityPrice;
		this.brand = brand;
		this.categories = categories;
		this.orderDate = orderDate;
		this.orderUpdate = orderUpdate;
		this.status = status;
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantityPrice() {
		return quantityPrice;
	}

	public void setQuantityPrice(int quantityPrice) {
		this.quantityPrice = quantityPrice;
	}

	public BrandReponse getBrand() {
		return brand;
	}

	public void setBrand(BrandReponse brand) {
		this.brand = brand;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderUpdate() {
		return orderUpdate;
	}

	public void setOrderUpdate(String orderUpdate) {
		this.orderUpdate = orderUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CategoryResponse> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryResponse> categories) {
		this.categories = categories;
	}	
}
