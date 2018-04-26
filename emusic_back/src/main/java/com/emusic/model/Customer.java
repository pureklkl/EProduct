package com.emusic.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -352698528029905792L;

	@Id
    @GeneratedValue
    private Long customerId;
    
    @NotEmpty (message = "The customer first name must not be null.")
    private String firstName;
    
    @NotEmpty (message = "The customer last name must not be null.")
    private String lastName;

    @NotEmpty (message = "The customer email must not be null.")
    private String customerEmail;
    private String customerPhone;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    @JoinColumn(name = "cartId")
    @JsonIgnore
    private Cart cart;
    
    @OneToOne(optional = true)
    @JoinColumn(name="billingAddressId", nullable = true)
    private BillingAddress billingAddress;

    @OneToOne(optional = true)
    @JoinColumn(name="shippingAddressId", nullable = true)
    private ShippingAddress shippingAddress;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<CustomerOrder> orders;
    
    public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private boolean enabled;

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

	public List<CustomerOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}
    
	
	
}
