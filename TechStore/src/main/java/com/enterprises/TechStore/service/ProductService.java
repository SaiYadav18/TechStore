package com.enterprises.TechStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	public Product saveProduct(Product product) {

		return repository.save(product);
	}
	
	public Page<Product> getProduct(int page, int size){
		
		Pageable pageable = PageRequest.of(page, size);
		
		return repository.findAll(pageable);
		
	}

}
