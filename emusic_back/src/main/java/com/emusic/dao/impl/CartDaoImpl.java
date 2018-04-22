package com.emusic.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emusic.dao.CartDao;
import com.emusic.dao.exception.InvalidCartException;
import com.emusic.model.Cart;

@Repository
@Transactional
public class CartDaoImpl extends DaoImpl implements CartDao {

	@Override
	public Cart getCartById(Long cartId) {
		Session session = getSession();
		return session.get(Cart.class, cartId);
	}

	@Override
	public void updateCart(Cart cart) {
		Session session = getSession();
		session.update(cart);
		session.flush();
	}
	
	public Cart validateCartById(int cartId) {
		Session session = getSession();
		Cart cart = session.get(Cart.class, cartId);
		if(cart == null || cart.getItems().size() == 0) {
			throw new InvalidCartException();
		}
		return cart;
	}
}
