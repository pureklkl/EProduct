package com.emusic.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4451577432355620526L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	String id;
	
	@NotEmpty (message="Product name should not be empty.")
	String name;
	String category;
	String status;
	String condition_;
	String manufactory;
	String description;
	
	@Min (value=0, message="Product price should not be negative.")
	int price;
	
	@Min (value=0, message="Product unit should not be negative.")
	int unit;
	
	@Transient
	MultipartFile image;
	
    @OneToMany(mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval=true)
    @JsonIgnore
    private List<CartItem> cartItemList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String catagory) {
		this.category = catagory;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getManufactory() {
		return manufactory;
	}
	public void setManufactory(String manufacotry) {
		this.manufactory = manufacotry;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCondition_() {
		return condition_;
	}
	public void setCondition_(String condition_) {
		this.condition_ = condition_;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public List<CartItem> getCartItemList() {
		return cartItemList;
	}
	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}
	
	
	
}
