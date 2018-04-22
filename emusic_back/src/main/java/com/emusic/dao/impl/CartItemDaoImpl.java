package com.emusic.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emusic.dao.CartItemDao;
import com.emusic.model.Cart;
import com.emusic.model.CartItem;

@Repository
@Transactional
public class CartItemDaoImpl extends DaoImpl implements CartItemDao {

	@Override
	public Long addCartItem(CartItem cartItem) {
        Session session = getSession();
        session.saveOrUpdate(cartItem);
        session.flush();
        return cartItem.getCartItemId();
	}

	@Override
	public void removeCartItem(CartItem cartItem) {
        Session session = getSession();
        session.delete(cartItem);
        session.flush();
		
	}

	@Override
	public void removeAllCartItems(Cart cart) {
		Session session = getSession();
		cart = session.get(Cart.class, cart.getId());
		List<CartItem> items = cart.getItems();
		for(CartItem item : items) {
			session.remove(item);
		}
        session.flush();
	}

	@Override
	public CartItem getCartItemById(Long id) {
		Session session = getSession();
		CartItem cartItem = session.get(CartItem.class, id);
		session.flush();
		return cartItem;
	}

	@Override
	public void removeCartItemById(Long id) {
        Session session = getSession();
        session.delete(getCartItemById(id));
        session.flush();
	}

}
