package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>, JpaRepository<UserEntity, Long>{
	
	/*
		JPA를 사용하는 장점은 메소드의 이름으로 Query를 실행할 수 있다는 점입니다. 
		CRUD 인터페이스를 상속받은 다음 find로 시작되는 메소드를 작성하게 되면, 
		해당 메소드의 이름을 테이블 컬럼명과 매칭하여 해당 SELECT Query를 실행할 수 있습니다. 
		예를 들어, findByLastnameAndFirstname 과 같은 메소드는 다음과 같은 Query과 실행됩니다. 

		where x.lastname = ?1 and x.firstname = ?2

		https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference 에서 
		Query Creation 부분을 참고해 보시면 여러 패턴이 나와 있으니 참조하시기 바랍니다. 

		감사합니다. 
	 */
	UserEntity findByUserId(String userId);
//	UserEntity findXXByUserId(String userId);
//	UserEntity findXX1ByUserId(String userId);
//	UserEntity findXX2ByUserId(String userId);
//	UserEntity findXX3ByUserIdandEmail(String userId);
	
}
