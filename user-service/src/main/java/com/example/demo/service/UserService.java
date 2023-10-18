package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.jpa.UserEntity;

public interface UserService {
	UserDto createUser(UserDto userDto); // userDTO 를 반환하고 userDto 를 받아서 db 에 저장한다.
	
	UserDto getUserByUserId(String userId);
	
	Iterable<UserEntity> getUserByAll(); // db 에 있는 자료를 직접 가공한다 할 때는 UserDTO 로 리턴 값 || 가공하지 않는다면 UserEntity
}
