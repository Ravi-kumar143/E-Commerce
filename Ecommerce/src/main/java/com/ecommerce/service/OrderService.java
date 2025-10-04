package com.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ecommerce.entity.Order;
import com.ecommerce.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
//	public Order createProduct(OrderDto dto) {
//		Order order = new Order();
//		order.set(null);
//		
//	}

}
