package com.proxtechshop.utils;

import java.io.Serializable;

public class ProductCategoryId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String productId;
	
	private int categoryid;

	public ProductCategoryId(String productId, int categoryid) {
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
