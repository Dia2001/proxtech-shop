package com.proxtechshop.viewmodels;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.OrderDetail;
import com.proxtechshop.entities.OrderStatus;
import com.proxtechshop.entities.Product;

public class OrderViewModel {

private String id;
	
	private String nameShip;
	
	private String addressShip;
	
	private String phoneShip;

	private String note;

	private BigDecimal checkoutPrice;
	
	private BigDecimal promotion;

	private Date createdDate;
	
	private Date updatedDate;
	
	private OrderStatus orderStatus;
	
	private Set<OrderDetail> orderDetails = new HashSet<>();
	
	private Customer customer;
	
	private List<Product> products;

	public  OrderViewModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameShip() {
		return nameShip;
	}

	public void setNameShip(String nameShip) {
		this.nameShip = nameShip;
	}

	public String getAddressShip() {
		return addressShip;
	}

	public void setAddressShip(String addressShip) {
		this.addressShip = addressShip;
	}

	public String getPhoneShip() {
		return phoneShip;
	}

	public void setPhoneShip(String phoneShip) {
		this.phoneShip = phoneShip;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getCheckoutPrice() {
		return checkoutPrice;
	}

	public void setCheckoutPrice(BigDecimal checkoutPrice) {
		this.checkoutPrice = checkoutPrice;
	}

	public BigDecimal getPromotion() {
		return promotion;
	}

	public void setPromotion(BigDecimal promotion) {
		this.promotion = promotion;
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "OrderViewModel [id=" + id + ", nameShip=" + nameShip + ", addressShip=" + addressShip + ", phoneShip="
				+ phoneShip + ", note=" + note + ", checkoutPrice=" + checkoutPrice + ", promotion=" + promotion
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", orderStatus=" + orderStatus
				+ ", orderDetails=" + orderDetails + ", customer=" + customer + ", products=" + products + "]";
	}

	
}
