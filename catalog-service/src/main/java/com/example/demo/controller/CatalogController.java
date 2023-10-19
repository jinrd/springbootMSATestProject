package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.CatalogEntity;
import com.example.demo.service.CatalogService;
import com.example.demo.vo.ResponseCatalog;

@RestController
@RequestMapping(value = "/catalog-service")
public class CatalogController {
	Environment env;
	CatalogService catalogService;
	
	public CatalogController(Environment env, CatalogService catalogService) {
		this.env = env;
		this.catalogService = catalogService;
	}

	@GetMapping("/catalogs")
	public ResponseEntity<List<ResponseCatalog>> getCatalogs(){
		Iterable<CatalogEntity> catalogList = catalogService.getAllCatalogs();
		List<ResponseCatalog> result = new ArrayList<>();
		catalogList.forEach(v ->{
			result.add(new ModelMapper().map(v,ResponseCatalog.class));
		});
		
		return new ResponseEntity<List<ResponseCatalog>>(result, HttpStatus.OK);
	}
}