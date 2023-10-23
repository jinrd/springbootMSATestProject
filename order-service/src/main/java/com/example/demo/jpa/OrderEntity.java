package com.example.demo.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

//@Data
//@Entity
//@Table(name = "orders")
//public class OrderEntity implements Serializable{
//	
//	/*
//	 * 직렬화의 목적
//	 * 가지고 있는 Object(객체)를 전송하거나, 다른 네트워크로 전송하거나 데이터베이스 보관하기 위해 마셜링 작업하기 위해서 써준다.
//	 */
//	
//	// 엔티티 정의
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@Column(nullable = false, length = 120, unique = true)
//	private String productId;
//	
//	@Column(nullable = false)
//	private String qty;
//	
//	@Column(nullable = false)
//	private Integer unitPrice;
//	
//	@Column(nullable = false)
//	private Integer totalPrice;
//	
//	@Column(nullable = false)
//	private String userId;
//	
//	@Column(nullable = false, unique = true)
//	private String orderId;
//	
//	@Column(nullable = false, updatable = false, insertable = true)
//	@ColumnDefault(value="CURRENT_TIMESTAMP")
//	private Date createdAt;
//	
//	
//}
@Data
@Entity
@Table(name="orders")
public class OrderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String productId;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, unique = true)
    private String orderId;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;
}