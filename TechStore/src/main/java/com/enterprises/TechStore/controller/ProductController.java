package com.enterprises.TechStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprises.TechStore.entity.Product;
import com.enterprises.TechStore.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/products")
    public List<Product> getAllProducts(){
		
		return service.getAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Integer id){

	    return service.getProduct(id);

	}
}
