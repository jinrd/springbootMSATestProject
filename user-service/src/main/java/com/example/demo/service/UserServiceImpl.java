package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.jpa.UserEntity;
import com.example.demo.jpa.UserRepository;
import com.example.demo.vo.ResponseOrder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	UserRepository userRepository;
	BCryptPasswordEncoder passwordEncoder;
	
	/*
	  Autowired 라는 것은 사용자에 의해서 개발자에 의해 인스턴스가 만들어지는 것이 아니라
	  Spring 에 컨텍스트가 기동이 되면서 자동으로 등록할 수 잇는 빈들을 찾아 메모리에 ㄱ개체를 생성해주는 과
	  자동으로 등록해주는 과
	  
	  유저 서비스 인플이라는 서비스 빈이 자동으로 메모리에 등록이 되는데 메모리에 등록이 될때
	  인스턴스가 만들어 질 때 생성자가 호출된다.
	  생성자 안에 선언되어 있었던 인스턴스들도 초기화가 같이 되어야지만 주입이 가능해진다.
	  
	  현재 BCryptPasswordEncoder 는 빈으로 주입을 한적이 없기 때문에 오류가 발생한다.
	  
	  스프링 부트에 와서 초기 기동할 수 있는 클래스를 우리가 제어할 수 있게 되었다.(Application 클래스에서 Bean 설정이 가능하다)
	 */
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder ) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDto createUser(UserDto userDto) {
		log.info("UserServiceImpl");
		userDto.setUserId(UUID.randomUUID().toString());
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = mapper.map(userDto, UserEntity.class);
		
		userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));
		userRepository.save(userEntity);
		
		UserDto returnUserDto  = mapper.map(userEntity, UserDto.class);
		
		return returnUserDto;
	}

	
	// DB 에서 userId 를 가져오는 부분
	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		// 데이터 유무 확인
		if(userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		
		List<ResponseOrder> orders = new ArrayList<>();
		
		userDto.setOrders(orders);
		
		return userDto;
	}

	
	// DB 에서 전체 데이터를 가져오는 부
	@Override
	public Iterable<UserEntity> getUserByAll() {
		/*
		 레퍼지토리 안에 FindAll 이라는 함수는 기본으로 제공되는 함수이다.
		 전체 데이터를 리턴
		 */
		return userRepository.findAll();
	}
	
	

}
