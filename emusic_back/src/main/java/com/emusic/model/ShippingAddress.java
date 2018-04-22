package com.emusic.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ShippingAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7571224234465147586L;
	@Id
    @GeneratedValue
    private Long shippingAddressId;
    @NotNull
    private String streetName;
    @NotNull
    private String apartmentNumber;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;
    @NotNull
    private String zipCode;

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
        final ShippingAddress address = (ShippingAddress) obj;
        
        return streetName.equals(address.streetName)
        		&& apartmentNumber.equals(address.apartmentNumber)
        		&& city.equals(address.city)
        		&& state.equals(address.state)
        		&& country.equals(address.country)
        		&& zipCode.equals(address.zipCode);
    }
    
    
	public Long getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(Long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
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
    
}
