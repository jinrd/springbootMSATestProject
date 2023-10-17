package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestServiceImpl implements UserService{

	@Override
	public UserDto createUser(UserDto userDto) {
		log.info("TestServiceImpl");
		return null;
	}

}
