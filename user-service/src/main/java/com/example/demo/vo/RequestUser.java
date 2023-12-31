package com.example.demo.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RequestUser {
	
	/*
	 * Validation 속성을 사용하고 싶을 때 not null 이어야 한다.
	 */
	
	@NotNull(message = "Email cannot be null")
	@Size(min=2, message="Email not be less than two characters")
	@Email
	private String email;

	@NotNull(message = "Name cannot be null")
	@Size(min=2, message="Name not be less than two characters")
	private String name;
	
	@NotNull(message = "Password cannot be null")
	@Size(min=8, message="Password must be equal or grater than 8 characters")
	private String pwd;
}
