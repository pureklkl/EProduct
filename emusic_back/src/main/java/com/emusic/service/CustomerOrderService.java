package com.emusic.service;

import java.util.List;

import com.emusic.model.CustomerOrder;

public interface CustomerOrderService {
	CustomerOrder createCustomerOrderFromCart(Long cartId);
	void addCustomerOrder(CustomerOrder cart);
	CustomerOrder getCustomerOrderById(Long id);
	List<CustomerOrder> getOrdersByUserEmail(String email);
	CustomerOrder getOrderById(Long id);
	void updateOrder(CustomerOrder order);
	List<CustomerOrder> getOrders();
}
