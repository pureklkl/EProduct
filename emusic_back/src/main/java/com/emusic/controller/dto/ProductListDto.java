package com.emusic.controller.dto;

import java.util.List;

import com.emusic.model.Product;

public class ProductListDto {
	long total;
	
	List<Product> productList;
	public ProductListDto() {
		// TODO Auto-generated constructor stub
	}
	public Long getTotal() {
		return total;
	}
	
	public ProductListDto(long total, List<Product> productList) {
		super();
		this.total = total;
		this.productList = productList;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
}
