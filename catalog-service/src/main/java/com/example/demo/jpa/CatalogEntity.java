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


@Data
@Entity
@Table(name = "catalog")
public class CatalogEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 120, unique = true)
	private String productId;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private int stock;
	
	@Column(nullable = false)
	private int unitPrice;
	
	@Column(nullable = false, updatable = false, insertable = false)
	@ColumnDefault(value = "CURRENT_TIMESTAMP") // H2 DB에서 현재 날짜를 가져오기 위한 값
	private Date createdAt;
	
}
