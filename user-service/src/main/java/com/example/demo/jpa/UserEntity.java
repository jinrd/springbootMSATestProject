package com.example.demo.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity // 아무것도 없는 상태에서 Entity 클래스는 데이터베이스로 만들어져야 하는 요소
@Table(name= "users") // 이름은 되도록 명시
public class UserEntity {

	// 데이터베이스에 저장될 컬럼들을 정의하는 부분
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 생성된느 전략을 지정할 수 있다.
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String userId;
	
	@Column(nullable = false, unique = true )
	private String encryptedPwd;
	
}
