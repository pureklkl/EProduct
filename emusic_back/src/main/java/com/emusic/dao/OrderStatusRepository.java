package com.emusic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emusic.model.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
	OrderStatus findByName(String name);
    @Override
    void delete(OrderStatus orderStatus);
}
