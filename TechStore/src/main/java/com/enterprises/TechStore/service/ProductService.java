package com.enterprises.TechStore.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enterprises.TechStore.entity.Product;
import com.enterprises.TechStore.exception.ProductNotFoundException;
import com.enterprises.TechStore.repository.ProductRepository;

@Service
public class ProductService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	ProductRepository repository;

	public List<Product> getAllProducts() {
		
		log.info("Fetching products");

		return repository.findAll();

	}

	public Product getProduct(int id) {
		
		log.info("Fetching product with id: {}", id);

		return repository.findById(id).orElseThrow(() ->
        new ProductNotFoundException("Product not found with id : " + id));

	}

	public Product saveProduct(Product product) {
		
		 log.info("Creating product: {}", product.getProductName());
		 
		 log.info("Product created successfully with id: {}",
				 product.getId());

		return repository.save(product);
		
	}
	
	public Page<Product> getProduct(int page, int size){
		
		Pageable pageable = PageRequest.of(page, size);
		
		log.info("Fetching products");
		
		return repository.findAll(pageable);
		
	}
	
	public Page<Product> searchProducts(String keyword, Pageable pageable) {

	    return repository
	            .findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
	                    keyword,
	                    keyword,
	                    pageable);

	}

}
