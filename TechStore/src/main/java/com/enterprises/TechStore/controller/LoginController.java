package com.enterprises.TechStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprises.TechStore.dto.LoginRequest;
import com.enterprises.TechStore.entity.User;
import com.enterprises.TechStore.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LoginController {

    @Autowired
    UserService service;
    
    @GetMapping("/getname")
    public String getName() {
    	
    	return "saikohli";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
    	
    	System.out.println("request :"+request.toString());

        User user = service.login(
                request.getUsername(),
                request.getPassword());

        if(user!=null){

            return ResponseEntity.ok(user);

        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid Username or Password");

    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){

        String message=service.register(user);

        if(message.equals("Registration Successful")){

            return ResponseEntity.ok(message);

        }

        return ResponseEntity
                .badRequest()
                .body(message);

    }

}
