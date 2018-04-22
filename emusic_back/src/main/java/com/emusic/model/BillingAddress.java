package com.emusic.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BillingAddress implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6222159285111124582L;
	@Id
    @GeneratedValue
    private Long billingAddressId;
    private String streetName;
    private String apartmentNumber;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    
    @ManyToOne
    @JsonIgnore
    private Customer customer;

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
        final BillingAddress address = (BillingAddress) obj;
        
        return streetName.equals(address.streetName)
        		&& apartmentNumber.equals(address.apartmentNumber)
        		&& city.equals(address.city)
        		&& state.equals(address.state)
        		&& country.equals(address.country)
        		&& zipCode.equals(address.zipCode);
    }
    
    
	public Long getBillingAddressId() {
		return billingAddressId;
	}
	public void setBillingAddressId(Long billingAddressId) {
		this.billingAddressId = billingAddressId;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    
	
}
