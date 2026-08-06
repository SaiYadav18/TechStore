package com.enterprises.TechStore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprises.TechStore.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	  Page<Product> findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
	            String productName,
	            String description,
	            Pageable pageable);

	

}
