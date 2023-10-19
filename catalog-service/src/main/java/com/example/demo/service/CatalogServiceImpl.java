package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.jpa.CatalogEntity;
import com.example.demo.jpa.CatalogRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService{

	CatalogRepository catalogRepository;
	
	public CatalogServiceImpl(CatalogRepository catalogRepository) {
		this.catalogRepository = catalogRepository;
	}
	
	@Override
	public Iterable<CatalogEntity> getAllCatalogs() {
		
		return catalogRepository.findAll();
	}

	
}
