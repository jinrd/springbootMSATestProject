package com.example.demo.service;

import com.example.demo.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto); // userDTO 를 반환하고 userDto 를 받아서 db 에 저장한다.
}
