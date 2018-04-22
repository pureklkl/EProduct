package com.emusic.service;

import com.emusic.model.OrderStatus;

public interface OrderStatusService {
	OrderStatus findByName(String name);
}
