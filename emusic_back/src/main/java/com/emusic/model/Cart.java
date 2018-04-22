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
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart implements Serializable  {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4815623093962135272L;

	@Id
    @GeneratedValue
	private Long id;
	
    @OneToMany(mappedBy = "cart", cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval=true)
	private List<CartItem> items;
    
    @OneToOne
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private Customer customer;
    
    @Min(value = 0)
	private int totalPrice;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
