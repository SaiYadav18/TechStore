package com.enterprises.TechStore.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(ProductNotFoundException.class)
	 public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex){
		 
		 return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
		 
	 }
	 
	 @ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<String> handleUserNotFound(ResourceNotFoundException ex){
		 return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(NoResourceFoundException.class)
	    public ResponseEntity<Map<String, Object>> handleNotFound(
	            NoResourceFoundException ex) {
	 
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", 404);
	        response.put("message", "API not found");
	 
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(response);
	    }
	
}
