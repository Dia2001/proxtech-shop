package com.proxtechshop.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length = 10, nullable = false)
	private String id;
	
	@Column(name = "name_ship", length = 100, nullable = false)
	private String nameShip;
	
	@Column(name = "address_ship", length = 256, nullable = false)
	private String addressShip;
	
	@Column(name = "phone_ship", length = 11, nullable = false)
	private String phoneShip;

	@Column(name = "note", length = 500, nullable = false)
	private String note;

	@Column(name = "checkout_price", precision = 11, scale = 0, nullable = false)
	private BigDecimal checkoutPrice;
	
	@Column(name = "promotion", precision = 11, scale = 0, nullable = false)
	private BigDecimal promotion;

	@Column(name = "created_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "updated_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@ManyToOne
	@JoinColumn(name = "order_status_key", nullable = false)
	private OrderStatus orderStatus;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@OneToMany(mappedBy = "order")
	private Set<OrderDetail> orderDetails = new HashSet<>();

	@OneToMany(mappedBy = "order")
	private Set<OrderHistory> orderHistories = new HashSet<>();

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public Set<OrderHistory> getOrderHistories() {
		return orderHistories;
	}

	public void setOrderHistories(Set<OrderHistory> orderHistories) {
		this.orderHistories = orderHistories;
	}	
}
