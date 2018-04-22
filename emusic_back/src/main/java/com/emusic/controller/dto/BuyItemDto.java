package com.emusic.controller.dto;

import javax.validation.constraints.NotNull;

public class BuyItemDto {
	@NotNull
	private String productId;
	@NotNull
	private Long quantity;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
}
