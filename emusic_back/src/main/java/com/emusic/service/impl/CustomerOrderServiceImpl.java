package com.emusic.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emusic.dao.CartItemDao;
import com.emusic.dao.CustomerDao;
import com.emusic.dao.CustomerOrderDao;
import com.emusic.dao.OrderStatusRepository;
import com.emusic.model.BillingAddress;
import com.emusic.model.Cart;
import com.emusic.model.CartItem;
import com.emusic.model.Customer;
import com.emusic.model.CustomerOrder;
import com.emusic.model.OrderItem;
import com.emusic.model.ShippingAddress;
import com.emusic.service.CartService;
import com.emusic.service.CustomerOrderService;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CustomerOrderDao orderDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	CartItemDao cartItemDao;
	
	@Autowired
	OrderStatusRepository orderStatusDao;
	
	@Override
	public CustomerOrder createCustomerOrderFromCart(Long cartId) {
		// TODO Auto-generated method stub
        Cart cart = cartService.getCartById(cartId);
        
        CustomerOrder customerOrder = new CustomerOrder();
        List<OrderItem> orderItemList = new ArrayList<>();
        int totalPrice = 0;
        for(CartItem cartItem : cart.getItems()) {
        	OrderItem orderItem = new OrderItem();
        	orderItem.setOrder(customerOrder);
        	orderItem.setProduct(cartItem.getProduct());
        	orderItem.setQuantity(cartItem.getQuantity());
        	orderItem.setTotalPrice(cartItem.getTotalPrice());
        	orderItemList.add(orderItem);
        	totalPrice += cartItem.getTotalPrice();
        }
        
        customerOrder.setItems(orderItemList);
        customerOrder.setTotalPrice(totalPrice);
        
        Customer customer = cart.getCustomer();
        customerOrder.setCustomer(customer);
        customerOrder.setBillingAddress(customer.getBillingAddress());
        customerOrder.setShippingAddress(customer.getShippingAddress());
        if(customerOrder.getBillingAddress() == null) {
        	customerOrder.setBillingAddress(new BillingAddress());
        }
        if(customerOrder.getShippingAddress() == null) {
        	customerOrder.setShippingAddress(new ShippingAddress());
        }
        return customerOrder;
	}

	@Override
	public void addCustomerOrder(CustomerOrder order) {
		if(!order.getBillingAddress().equals(order.getCustomer().getBillingAddress())) {
			order.getBillingAddress().setBillingAddressId(null);
		}
		if(!order.getShippingAddress().equals(order.getCustomer().getShippingAddress())) {
			order.getShippingAddress().setShippingAddressId(null);
		}
		
		order.setStatus(orderStatusDao.findByName("ORDER STARTED"));
		
		Customer customer = order.getCustomer();
		
		orderDao.addCustomerOrder(order);
		
		customer.setBillingAddress(order.getBillingAddress());
		order.getBillingAddress().setCustomer(customer);
		customer.setShippingAddress(order.getShippingAddress());
		
		customerDao.updateCustomer(customer);
		
		cartItemDao.removeAllCartItems(customer.getCart());
	}
	
	@Override
	public CustomerOrder getCustomerOrderById(Long id) {
		// TODO Auto-generated method stub
		return orderDao.getOrderById(id);
	}
	
	public ShippingAddress validShippingAddress(@Valid ShippingAddress address) {
		return address;
	}

	@Override
	public List<CustomerOrder> getOrdersByUserEmail(String email) {
		 return orderDao.getCustomerOrder(customerDao.getCustomerByEmail(email));
	}

	@Override
	public CustomerOrder getOrderById(Long id) {
		return orderDao.getOrderById(id);
	}

	@Override
	public void updateOrder(CustomerOrder order) {
		orderDao.updateOrder(order);
	}

	@Override
	public List<CustomerOrder> getOrders() {
		return orderDao.getOrders();
	}

}
