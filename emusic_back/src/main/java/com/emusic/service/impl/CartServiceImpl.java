package com.emusic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emusic.dao.CartDao;
import com.emusic.dao.CartItemDao;
import com.emusic.dao.ProductDao;
import com.emusic.dao.UserDao;
import com.emusic.model.Cart;
import com.emusic.model.CartItem;
import com.emusic.model.Product;
import com.emusic.model.User;
import com.emusic.service.CartService;

@Service
public class CartServiceImpl implements CartService  {
	@Autowired
	UserDao userDao;
	@Autowired
	CartItemDao cartItemDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	CartDao cartDao;
	
	@Override
	public Cart getCartByUserId(String userId) {
		User user = userDao.getUserById(userId);
		if(user == null) {
			return null;
		}
		return user.getCustomer().getCart();
	}

	@Override
	public Cart getCartById(Long cartId) {
		return cartDao.getCartById(cartId);
	}

	@Override
	public Long addItem(Cart cart, Long productId, int quantity) {
		List<CartItem> cartItems = cart.getItems();
        for (CartItem cartItem : cartItems) {
        	Product product = cartItem.getProduct();
            if(productId.equals(product.getId())){
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
                cartItemDao.addCartItem(cartItem);
                return cartItem.getCartItemId();
            }
        }
        
        Product product = productDao.getProductById(productId);
        
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
        cartItem.setCart(cart);
        return cartItemDao.addCartItem(cartItem);
	}

	@Override
	public void putItem(Long itemId, int quantity) {
		CartItem cartItem = cartItemDao.getCartItemById(itemId);
		cartItem.setQuantity(quantity);
		cartItem.setTotalPrice(quantity * cartItem.getProduct().getPrice());
		cartItemDao.addCartItem(cartItem);
	}

	@Override
	public void removeItem(Long itemId) {
		cartItemDao.removeCartItemById(itemId);
	}

	@Override
	public void clearCart(Cart cart) {
		cartItemDao.removeAllCartItems(cart);
	}
	/*
	private int sumPrice(Cart cart) {
		int sum = 0;
		for(CartItem cartItem : cart.getItems()) {
			sum += cartItem.getTotalPrice();
		}
		return sum;
	}*/

}
