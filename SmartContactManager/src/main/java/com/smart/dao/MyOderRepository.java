package com.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.entities.MyOrder;

public interface MyOderRepository extends JpaRepository<MyOrder, Long>{

	
	public MyOrder findByOrderId(String orderId);
}
