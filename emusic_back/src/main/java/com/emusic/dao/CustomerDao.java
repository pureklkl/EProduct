package com.emusic.dao;

import com.emusic.model.Customer;
import com.emusic.model.User;

public interface CustomerDao {
	Long addCustomer(Customer customer, User user);
	Customer getCustomerByEmail(String email);
	Customer getCustomerById(Long id);
	void updateCustomer(Customer customer);
}
