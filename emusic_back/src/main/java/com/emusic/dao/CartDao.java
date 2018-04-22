package com.emusic.dao;

import com.emusic.model.Cart;
import com.emusic.model.User;

public interface CartDao {
	Cart getCartById(Long cartId);
	void updateCart(Cart cart);
}
