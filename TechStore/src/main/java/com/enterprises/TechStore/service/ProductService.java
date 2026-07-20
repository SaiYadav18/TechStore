package com.enterprises.TechStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprises.TechStore.entity.Product;
import com.enterprises.TechStore.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	public List<Product> getAllProducts() {
		
		return repository.findAll();
		
	}
	
	public Product getProduct(int id) {
		
		return repository.findById(id).orElse(null);
		
	}

}
