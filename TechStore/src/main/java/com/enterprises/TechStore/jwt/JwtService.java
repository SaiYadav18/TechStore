
package com.enterprises.TechStore.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey123456";

	private Key getSignKey() {

		return Keys.hmacShaKeyFor(SECRET.getBytes());

	}
	

	public String generateToken(UserDetails userDetails) {

		return Jwts.builder()

				.setSubject(userDetails.getUsername())

				.setIssuedAt(new Date())

				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)

				.compact();

	}

	public String extractUsername(String token) {

		return extractClaim(token, Claims::getSubject);

	}

	public Date extractExpiration(String token) {

		return extractClaim(token, Claims::getExpiration);

	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = extractAllClaims(token);

		return claimsResolver.apply(claims);

	}
	

    public String generateAccessToken( String username) {

        return Jwts.builder()
        		.setSubject(username)
//                .subject(email)
                .setIssuedAt(new Date())
                .setIssuer("auth-service")
                .claim("type", "accessToken")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(getSignKey())
                .compact();
    }

    public String generateRefreshToken( String username) {

        return Jwts.builder()
                .setSubject(username)
//                .claim("email", email)
                .setIssuedAt(new Date())
                .setIssuer("auth-service")
                .claim("type", "refreshToken")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
//                .signWith(getKey())
                .compact();
    }

	Claims extractAllClaims(String token) {

		return Jwts.parserBuilder()

				.setSigningKey(getSignKey())

				.build()

				.parseClaimsJws(token)

				.getBody();

	}

	private boolean isTokenExpired(String token) {

		return extractExpiration(token).before(new Date());

	}

	public boolean isTokenValid(String token, UserDetails userDetails)

	{

		String username = extractUsername(token);

		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

	}
	
	public boolean validateAccessToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            String type = claims.get("type", String.class);
            Date expiration = claims.getExpiration();
            return "accessToken".equals(type)
            		&& expiration != null
                    && expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
	

}
