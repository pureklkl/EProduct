package com.emusic.controller.dto;

import javax.validation.constraints.NotNull;

public class BuyItemDto {
	@NotNull
	private Long productId;
	@NotNull
	private Long quantity;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
}
