package com.emusic.service;

import com.emusic.model.Cart;

public interface CartService {
	Cart getCartByUserId(String UserId);
	Cart getCartById(Long cartId);
	Long addItem(Cart cart, String productId, int quantity);
	void putItem(Long itemId, int quantity);
	void removeItem(Long itemId);
	void clearCart(Cart cart);
}
