package com.example.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {
	
	/*
	 * 처음에 입력받은 데이터와 다른 값을 추가해서 중간 단계 클래스로 이동이 될 때 사용하겠다는 의미 DTO
	 */
	private String email;
	private String name;
	private String pwd;
	private String userId;
	private Date createdAt;
	
	private String encryptedPwd;
	
}
