package com.emusic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CustomerOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8873257362732148361L;

	@Id
    @GeneratedValue
    private Long customerOrderId;
    
    @ManyToOne
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderItem> items;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billingAddressId")
    private BillingAddress billingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "shippingAddressId")
    private ShippingAddress shippingAddress;
    
    @Min(value = 0)
    int totalPrice;
    
    private String info;
    
    @ManyToOne
    @JoinColumn(name = "status_id")
    OrderStatus status;
    
    @Column(name = "startTime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    
    @Column(name = "updateTime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(Long customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	
	@PrePersist
	protected void onCreate() {
		startTime = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updateTime = new Date();}
	}
