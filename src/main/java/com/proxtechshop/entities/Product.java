package com.proxtechshop.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", length = 36, nullable = false)
	private String id;
	
	@Column(name = "brand_id", insertable = false, updatable = false, nullable = false)
	private int brandId;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "descriptioned", length = 500, nullable = true)
	private String descriptioned;
	
	@Column(name = "description", length = 500, nullable = false)
	private String description;
	
	@Column(name = "thumbnail", length = 500, nullable = false)
	private String thumbnail;
	
	@Column(name = "slug", length = 120, nullable = false)
	private String slug;
	
	@Column(name = "price", precision = 11, scale = 0, nullable = false)
	private BigDecimal price;
	
	@Column(name = "quantity", length = 11, nullable = false)
	private int quantity;
	
	@Column(name = "rate", precision = 2, scale = 1, nullable = true)
	private BigDecimal rate;
	
	@Column(name = "created_date", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "updated_date", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "product_categories",
			joinColumns = {
					@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, updatable = false)
			},
			inverseJoinColumns = {
					@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, updatable = false)
			}
	)
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "product")
	private Set<Image> images = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "product_attribute_values",
			joinColumns = {
					@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, updatable = false)
			},
			inverseJoinColumns = {
					@JoinColumn(name = "attribute_id", referencedColumnName = "id", nullable = false, updatable = false)
			}
	)
	private Set<ProductAttribute> productAttributes = new HashSet<>();
	
	@OneToMany(mappedBy = "product")
	private Set<ProductAttributeValue> productAttributeValues = new HashSet<>();

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "product_id")
//	private Set<OrderDetail> orderDetails = new HashSet<>();

	public Product() {
		super();
	}

	public Product(String id, String name,String descriptioned, String description, String thumbnail, String slug,
			BigDecimal price, int quantity, BigDecimal rate, Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.name = name;
		this.descriptioned=descriptioned;
		this.description = description;
		this.thumbnail = thumbnail;
		this.slug = slug;
		this.price = price;
		this.quantity = quantity;
		this.rate = rate;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
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

//	public Set<OrderDetail> getOrderDetails() {
//		return orderDetails;
//	}
//
//	public void setOrderDetails(Set<OrderDetail> orderDetails) {
//		this.orderDetails = orderDetails;
//	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", thumbnail=" + thumbnail
				+ ", slug=" + slug + ", price=" + price + ", quantity=" + quantity + ", rate=" + rate + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate;
	}

}
