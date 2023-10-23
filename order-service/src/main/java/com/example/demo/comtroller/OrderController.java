package com.example.demo.comtroller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.OrderDto;
import com.example.demo.jpa.OrderEntity;
import com.example.demo.service.OrderService;
import com.example.demo.vo.RequestOrder;
import com.example.demo.vo.ResponseOrder;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/order-service")
@Slf4j
public class OrderController {
	
	Environment env;
	OrderService orderService;
	
	public OrderController(Environment env, OrderService orderService) {
		this.env = env;
		this.orderService = orderService;
	}
	
//	@PostMapping(value = "/{userId}/orders")
//	public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId
//													,@RequestBody RequestOrder orderDetails){
//		ModelMapper mapper = new ModelMapper();
//		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		
//		OrderDto orderDto = mapper.map(orderDetails, OrderDto.class);
//		orderDto.setUserId(userId);
//		OrderDto createdOrder = orderService.createOrder(orderDto);
//		
//		ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
//	}
	
	 @PostMapping("/{userId}/orders")
	    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
	                                                     @RequestBody RequestOrder orderDetails) {
	        log.info("Before add orders data");
	        ModelMapper mapper = new ModelMapper();
	        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

	        OrderDto orderDto = mapper.map(orderDetails, OrderDto.class);
	        orderDto.setUserId(userId);
	        /* jpa */
	        OrderDto createdOrder = orderService.createOrder(orderDto);
	        ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);

	        log.info("After added orders data");
	        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
	    }
	
	@GetMapping(value = "/{userId}/orders")
	public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId){
		Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);
		
		List<ResponseOrder> result = new ArrayList<>();
		
		orderList.forEach(v -> {
			result.add(new ModelMapper().map(v, ResponseOrder.class));
		});
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
}
