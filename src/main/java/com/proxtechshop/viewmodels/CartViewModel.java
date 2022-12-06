package com.proxtechshop.viewmodels;

import java.math.BigDecimal;

import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.Product;

public class CartViewModel {

    private int id;
    
//	private String productId;
//	
//	private String customerId;
//	
	private BigDecimal price;
	
	private int quantity;
	
	private Customer customer;
	
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getProductId() {
//		return productId;
//	}
//
//	public void setProductId(String productId) {
//		this.productId = productId;
//	}
//
//	
//	public String getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartViewModel [id=" + id + ", price=" + price + ", quantity=" + quantity + ", customer=" + customer
				+ ", product=" + product + "]";
	}

	/* @Override */
	/*public String toString() {
		return "CartViewModel [id=" + id + ", productId=" + productId + ", price=" + price + ", quantity=" + quantity
				+ ", customer=" + customer + ", product=" + product + "]";
	}*/
	
	
}
