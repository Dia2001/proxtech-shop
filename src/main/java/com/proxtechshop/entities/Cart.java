package com.proxtechshop.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 11)
	private int id;
	
	@Column(name = "product_id", insertable = false, updatable = false, length = 36, nullable = false)
	private String productId;
	
	@Column(name = "customer_id", insertable = false, updatable = false, length = 36, nullable = false)
	private String customerId;
	
	@Column(name = "price", precision = 11, scale = 0, nullable = false)
	private BigDecimal price;
	
	@Column(name = "quantity", length = 11, nullable = false)
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
	private Product product;
	
	public Cart() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", productId=" + productId + ", customerId=" + customerId + ", price=" + price
				+ ", quantity=" + quantity + ", customer=" + customer + ", product=" + product + "]";
	}
	
}
