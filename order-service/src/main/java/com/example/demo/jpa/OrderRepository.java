package com.example.demo.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long>{
		OrderEntity findByOrderId(String orderId);
		Iterable<OrderEntity> findByUserId(String userId);
}
