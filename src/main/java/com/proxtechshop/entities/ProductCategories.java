package com.proxtechshop.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.proxtechshop.utils.ProductCategoryId;

import lombok.Data;

@Entity
@Table(name = "product_categories")
@IdClass(ProductCategoryId.class)
@Data
public class ProductCategories implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id", length = 36, nullable = false)
	private String productId;

	@Id
	@Column(name = "category_id", length = 11, nullable = false)
	private int categoryid;

	public ProductCategories(String productId, int categoryid) {
		super();
		this.productId = productId;
		this.categoryid = categoryid;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
}
