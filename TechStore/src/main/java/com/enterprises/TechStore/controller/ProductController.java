package com.enterprises.TechStore.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enterprises.TechStore.entity.Product;
import com.enterprises.TechStore.service.ProductService;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/api")
public class ProductController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService service;

	/*
	 * @GetMapping("/products") public List<Product> getAllProducts() {
	 * 
	 * return service.getAllProducts(); }
	 */

	@Value("${file.upload-dir}")
	private String uploadDir;

	@PostMapping("/products")
	public Product saveProduct(

	        @RequestParam String productName,

	        @RequestParam String description,

	        @RequestParam Double price,

	        @RequestParam MultipartFile image

	) throws IOException {

	    File uploadFolder = new File(uploadDir);

	    if (!uploadFolder.exists()) {
	        uploadFolder.mkdirs();
	    }

	    String fileName =
	            UUID.randomUUID() + "_" + image.getOriginalFilename();

	    File destination = new File(uploadFolder, fileName);
	    System.out.println("Upload Dir : " + uploadDir);

	    System.out.println("Folder Exists : " + new File(uploadDir).exists());

	    System.out.println("Absolute Path : " + new File(uploadDir).getAbsolutePath());

	    System.out.println("Destination : " + new File(uploadDir, fileName).getAbsolutePath());

	    image.transferTo(destination);

	    Product product = new Product();

	    product.setProductName(productName);
	    product.setDescription(description);
	    product.setPrice(price);
	    product.setImageUrl(fileName);

	    return service.saveProduct(product);
	}
	
	
	@GetMapping("/products")
	public Page<Product> getProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size){
		
		return service.getProduct(page, size);
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable int id) {
		
		return service.getProduct(id);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<Product>> searchProducts(

	        @RequestParam String keyword,

	        @RequestParam(defaultValue = "0") int page,

	        @RequestParam(defaultValue = "4") int size) {

	    Pageable pageable = PageRequest.of(page, size);

	    Page<Product> products = service.searchProducts(keyword, pageable);

	    return ResponseEntity.ok(products);
	}
	
	@GetMapping("/test-connection")
	@Transactional
	public String testConnection() throws InterruptedException {

		service.getAllProducts();

	    Thread.sleep(100000);

	    return "Connection released";
	}
}
