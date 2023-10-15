package com.example.demo.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
//@AllArgsConstructor
public class Greeting {
	
	@Value("${greeting.message}")
	private String message;
}
