package com.proxtechshop.viewmodels;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.proxtechshop.entities.Brand;
import com.proxtechshop.entities.Category;
import com.proxtechshop.entities.Image;
import com.proxtechshop.entities.ProductAttribute;
import com.proxtechshop.entities.ProductAttributeValue;

public class ProductDetailViewModel {
	
	private String id;
	private String name;
	private String descriptioned;
	private String description;
	private String thumbnail;
	private String slug;
	private BigDecimal price;
	private int quantity;
	private BigDecimal rate;
	private Date createdDate;
	private Date updatedDate;
	private Set<Category> categories = new HashSet<>();
	private Set<Image> images = new HashSet<>();
	private Brand brand;
	private Set<ProductAttribute> productAttributes = new HashSet<>();
	
	private Set<ProductAttributeValue> productAttributeValues = new HashSet<>();
	
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
	public String getDescriptioned() {
		return descriptioned;
	}
	public void setDescriptioned(String descriptioned) {
		this.descriptioned = descriptioned;
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
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	public Set<Image> getImages() {
		return images;
	}
	public void setImages(Set<Image> images) {
		this.images = images;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Set<ProductAttribute> getProductAttributes() {
		return productAttributes;
	}
	public void setProductAttributes(Set<ProductAttribute> productAttributes) {
		this.productAttributes = productAttributes;
	}
	public Set<ProductAttributeValue> getProductAttributeValues() {
		return productAttributeValues;
	}
	public void setProductAttributeValues(Set<ProductAttributeValue> productAttributeValues) {
		this.productAttributeValues = productAttributeValues;
	}
	
	@Override
	public String toString() {
		return "ProductDetailViewModel [id=" + id + ", name=" + name + ", descriptioned=" + descriptioned
				+ ", description=" + description + ", thumbnail=" + thumbnail + ", slug=" + slug + ", price=" + price
				+ ", quantity=" + quantity + ", rate=" + rate + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", categories=" + categories + ", images=" + images + ", brand=" + brand
				+ ", productAttributes=" + productAttributes + ", productAttributeValues=" + productAttributeValues
				+ "]";
	}
	
	
}
