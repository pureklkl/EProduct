package com.emusic.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emusic.controller.dto.UserRegDto;
import com.emusic.dao.CustomerDao;
import com.emusic.dao.RoleRepository;
import com.emusic.model.Cart;
import com.emusic.model.Customer;
import com.emusic.model.User;
import com.emusic.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private RoleRepository roleDao;
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public Customer getCustomerByEmail(String email) {
		return customerDao.getCustomerByEmail(email);
	}

	@Override
	public Long addNewCustomer(UserRegDto userDto) {
		
		Customer customer = new Customer();
		customer.setCustomerEmail(userDto.getEmail());
		customer.setFirstName(userDto.getFirstName());
		customer.setLastName(userDto.getLastName());
		customer.setCart(new Cart());
		customer.getCart().setCustomer(customer);
		
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setCustomer(customer);
		user.setRoles(Arrays.asList(roleDao.findByName("ROLE_USER")));
		
		Long customerId = customerDao.addCustomer(customer, user);
		
		return customerId;
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerDao.getCustomerById(id);
	}
	
}
