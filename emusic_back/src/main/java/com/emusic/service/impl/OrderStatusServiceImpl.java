package com.emusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emusic.dao.OrderStatusRepository;
import com.emusic.model.OrderStatus;
import com.emusic.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{
	@Autowired
	OrderStatusRepository orderStatusRep;
	
	@Override
	public OrderStatus findByName(String name) {
		return orderStatusRep.findByName(name);
	}



}
