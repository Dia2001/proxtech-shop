package com.proxtechshop.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", length = 36, nullable = false)
	private String id;
	
	@Column(name = "user_id", updatable = false, insertable = false, length = 36, nullable = false)
	private String userId;
	
	@Column(name = "full_name", length = 100,columnDefinition = "nvarchar", nullable = false)
	private String fullName;
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@Column(name = "phone", length = 11, nullable = true)
	private String phone;
	
	@Column(name = "address", length = 255, nullable = true)
	private String address;
	
	@Column(name = "image", length = 255, nullable = true)
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "created_date", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	private Set<Order> orders = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	private Set<Cart> carts = new HashSet<>();
	
	public Customer() {}
	
	public Customer(String id, String userId, String fullName, String email, String phone, String address,
			Date createdDate,String image) {
		super();
		this.id = id;
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.createdDate = createdDate;
		this.image=image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", phone="
				+ phone + ", address=" + address + ", createdDate=" + createdDate +  ", orders="
				+ orders + "]";
	}
}
