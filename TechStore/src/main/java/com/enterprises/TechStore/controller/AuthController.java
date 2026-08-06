/*
 * package com.enterprises.TechStore.controller;
 * 
 * import com.enterprises.TechStore.dto.LoginRequest; import
 * com.enterprises.TechStore.dto.LoginResponse; import
 * com.enterprises.TechStore.jwt.JwtService; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.web.bind.annotation.*;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/auth") public class AuthController {
 * 
 * @Autowired private AuthenticationManager authenticationManager;
 * 
 * @Autowired private JwtService jwtService;
 * 
 * @PostMapping("/login") public ResponseEntity<?> login(
 * 
 * @RequestBody LoginRequest request) {
 * 
 * Authentication authentication = authenticationManager.authenticate(
 * 
 * new UsernamePasswordAuthenticationToken(request.getUsername(),
 * request.getPassword()));
 * 
 * UserDetails userDetails = (UserDetails) authentication.getPrincipal();
 * 
 * String token = jwtService.generateToken(userDetails);
 * 
 * LoginResponse response = new LoginResponse(token, userDetails.getUsername(),
 * "USER");
 * 
 * return ResponseEntity.ok(response);
 * 
 * }
 * 
 * }
 */