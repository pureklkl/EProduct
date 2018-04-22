package com.emusic.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emusic.dao.CustomerDao;
import com.emusic.dao.exception.DuplicateEmailException;
import com.emusic.model.Customer;
import com.emusic.model.User;

@Repository
@Transactional
public class CustomerDaoImpl extends DaoImpl implements CustomerDao {

	@Override
	public Long addCustomer(Customer customer, User user) {
		Session session = getSession();
		if(getCustomerByEmail(user.getEmail(), session) != null) {
			throw new DuplicateEmailException();
		}
		session.save(customer);
		session.save(user);
		session.flush();
		return customer.getCustomerId();
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		Session session = getSession();
		return getUniqueField("customerEmail", email, Customer.class, session);
	}

	private Customer getCustomerByEmail(String email, Session session) {
		return getUniqueField("customerEmail", email, Customer.class, session);
	}	
	
	@Override
	public void updateCustomer(Customer cusotmer) {
		// TODO Auto-generated method stub
		Session session = getSession();
		session.saveOrUpdate(cusotmer);
		session.flush();
	}

	@Override
	public Customer getCustomerById(Long id) {
		Session session = getSession();
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

}
