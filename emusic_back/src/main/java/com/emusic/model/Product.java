package com.emusic.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.bridge.builtin.IntegerBridge;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Indexed
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4451577432355620526L;

	@Id
	@GeneratedValue
	Long id;
	
    @Fields({
        @Field, // @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)//default value
        @Field(name = "sorttitle", analyze = Analyze.NO, store = Store.NO, index = Index.NO)
    })
    @SortableField(forField = "sorttitle")
	@NotEmpty (message="Product name should not be empty.")
	String title;
	
    @Field
	String category;
	@Field
	String status;
	@Field
	String condition_;
	@Field
	String manufactory;
	
	String description;
	
    @Fields({
        @Field,
        @Field(name = "sortyear", analyze = Analyze.NO, store = Store.NO, index = Index.NO)
    })
    @SortableField(forField = "sortyear")
	@FieldBridge(impl = IntegerBridge.class)
	Integer year;
	
    @Fields({
        @Field,
        @Field(name = "sortdirctor", analyze = Analyze.NO, store = Store.NO, index = Index.NO)
    })
    @SortableField(forField = "sortdirctor")
	String dirctor;
	
	String banner_url;
	String trailer;
	
	@Field(name = "sortprice", analyze = Analyze.NO, store = Store.NO, index = Index.NO)
	@SortableField(forField = "sortprice")
	@Min (value=0, message="Product price should not be negative.")
	int price;
	
	@Min (value=0, message="Product unit should not be negative.")
	int unit;
	
	@Transient
	MultipartFile image;
	
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval=true)
    @JsonIgnore
    private List<OrderItem> orderItemList;
	
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval=true)
    @JsonIgnore
    private List<CartItem> cartItemList;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getDirctor() {
		return dirctor;
	}
	public void setDirctor(String dirctor) {
		this.dirctor = dirctor;
	}
	public String getBanner_url() {
		return banner_url;
	}
	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	
	
}
