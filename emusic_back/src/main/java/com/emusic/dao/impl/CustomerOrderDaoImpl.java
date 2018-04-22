package com.emusic.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emusic.dao.CustomerOrderDao;
import com.emusic.model.Customer;
import com.emusic.model.CustomerOrder;

@Repository
@Transactional
public class CustomerOrderDaoImpl extends DaoImpl implements CustomerOrderDao {

	@Override
	public Long addCustomerOrder(CustomerOrder order) {
		Session session = getSession();
		session.save(order);
		session.flush();
		return order.getCustomerOrderId();
	}

	@Override
	public CustomerOrder getOrderById(Long id) {
		Session session = getSession();
		
		CustomerOrder order = session.get(CustomerOrder.class, id);
		return order;
	}

	@Override
	public void updateOrder(CustomerOrder order) {
		Session session = getSession();
		session.update(order);
	}

	@Override
	public List<CustomerOrder> getOrders() {
		Session session = getSession();
		Query<CustomerOrder> query = session.createQuery("from CustomerOrder", CustomerOrder.class);
		List<CustomerOrder> result = query.list();
		return result;
	}

	@Override
	public List<CustomerOrder> getCustomerOrder(Customer customer) {
		Session session = getSession();
		customer = session.get(Customer.class, customer.getCustomerId());
		Hibernate.initialize(customer.getOrders());
		return customer.getOrders();
	}

}
