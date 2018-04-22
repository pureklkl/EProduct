package com.emusic.dao;

import com.emusic.model.Cart;
import com.emusic.model.CartItem;

public interface CartItemDao {
	CartItem getCartItemById(Long id);
	
    Long addCartItem(CartItem cartItem);
    
    void removeCartItemById(Long id);
    
    void removeCartItem(CartItem cartItem);

    void removeAllCartItems(Cart cart);

}
