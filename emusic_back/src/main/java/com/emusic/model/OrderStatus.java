package com.emusic.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderStatus implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7932690460532855453L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	@JsonIgnore
	private List<CustomerOrder> orders;

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * ((name == null) ? 0 : name.hashCode()) + 29;
    }
	
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderStatus orderStatus = (OrderStatus) obj;
        if (!orderStatus.getName().equals(getName())) {
            return false;
        }
        return true;
    }
	
	public OrderStatus() {
		super();
	}
	
	public OrderStatus(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CustomerOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}
	
	
}
