package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.jpa.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.vo.Greeting;
import com.example.demo.vo.RequestUser;
import com.example.demo.vo.ResponseUser;

@RestController
@RequestMapping("/user-service")
public class UserController {
	
	private Environment env;
	
	private UserService userService;
	
	@Autowired
	private Greeting greeting;
	
	@Autowired
	public UserController(Environment env, UserServiceImpl userService) {
		this.env = env;
		this.userService = userService;
	}
	
	@GetMapping("/health_check")
	public String status() {
		return String.format("It's Working in User Service on PORT %s"
				,env.getProperty("local.server.port"));
	}
	
	@GetMapping("/welcome")
	public String welcome(){
		return greeting.getMessage();
//		return env.getProperty("greeting.message");
	}
	
	@PostMapping("/users")
	public ResponseEntity<ResponseUser> createUser(@RequestBody @Valid RequestUser user) {
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = mapper.map(user, UserDto.class);
		
		userDto = userService.createUser(userDto);
		
		ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
		
		return new ResponseEntity<ResponseUser>(responseUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<ResponseUser>> getUsers(){
		Iterable<UserEntity> userList = userService.getUserByAll();
		
		List<ResponseUser> result = new ArrayList<>();
		
		userList.forEach(v -> {
			result.add(new ModelMapper().map(v, ResponseUser.class));
		});
		
		return new ResponseEntity<List<ResponseUser>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId){
		
		UserDto userDto = userService.getUserByUserId(userId);
		
		ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);
		
		return new ResponseEntity<ResponseUser>(returnValue,HttpStatus.OK);
	}
}
