package com.emusic.service;

import com.emusic.controller.dto.UserRegDto;
import com.emusic.model.Customer;

public interface CustomerService {
	Customer getCustomerByEmail(String email);
	Customer getCustomerById(Long id);
	Long addNewCustomer(UserRegDto userDto);
}
