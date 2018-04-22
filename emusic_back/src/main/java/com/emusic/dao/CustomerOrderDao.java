package com.emusic.dao;

import java.util.List;

import com.emusic.model.Customer;
import com.emusic.model.CustomerOrder;

public interface CustomerOrderDao {
	Long addCustomerOrder(CustomerOrder order);
	CustomerOrder getOrderById(Long id);
	void updateOrder(CustomerOrder order);
	List<CustomerOrder> getCustomerOrder(Customer customer);
	List<CustomerOrder> getOrders();
}
