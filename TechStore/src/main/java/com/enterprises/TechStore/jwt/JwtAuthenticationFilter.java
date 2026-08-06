
package com.enterprises.TechStore.jwt;



import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.enterprises.TechStore.entity.User;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.constraints.AssertFalse.List;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService  jwtUtil;
    
    

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        boolean valid = jwtUtil.validateAccessToken(token);

        if (valid) {
        	
        	Claims claims = jwtUtil.extractAllClaims(token);
        	

            String name = claims.get("subject", String.class);
//            String role = claims.get("role", String.class);
        	
        	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        			name, null, List.of(new SimpleGrantedAuthority("ROLE_" +name)));

//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(email, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)));
//
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
            
        }

        filterChain.doFilter(request, response);
    }
}


//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
////import com.enterprises.TechStore.service.CustomUserDetailsService;
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//	@Autowired
//	private JwtService jwtService;
//
////	@Autowired
////	private CustomUserDetailsService userDetailsService;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		String authHeader = request.getHeader("Authorization");
//
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//
//			filterChain.doFilter(request, response);
//			return;
//
//		}
//
//		String token = authHeader.substring(7);
//
//		String username = jwtService.extractUsername(token);
//
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
////			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
////
////			if (jwtService.isTokenValid(token, userDetails)) {
////
//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
////
////				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////
////				SecurityContextHolder.getContext().setAuthentication(authentication);
////
////			}
//
//		}
//
//		filterChain.doFilter(request, response);
//
//	}
//
//}
