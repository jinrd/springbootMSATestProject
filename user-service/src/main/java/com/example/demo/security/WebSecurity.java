package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 웹 시큐리티 용도로 사용할 것이다
public class WebSecurity{
	
	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/users/**").permitAll(); // 그냥 통과한다.
		http.headers().frameOptions().disable(); // 프레임상으로 구별되어 있는 html 페이지에서 무시가 되고 정상적으로 작동
		return http.build();
	}
}
