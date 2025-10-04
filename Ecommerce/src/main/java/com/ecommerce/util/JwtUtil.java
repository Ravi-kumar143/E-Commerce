package com.ecommerce.util;

import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final String SECRET_KEY = "mysecretkey123456";
	private final long EXPIRATION = 1000 * 60 * 60 * 10;
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
		
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		final Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
		
	}
	
	private Claims extractAllClaims(String token) {
			return Jwts.parser()
					.setSigningKey(SECRET_KEY)
					.parseClaimsJws(token)
					.getBody();
			
	}
	
	
	public Boolean isTokenExpried(String token) {
		return extractExpiration(token).before(new Date());
		
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	
	}
	
	public String generateToken(String username) {
		return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
			.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
			.compact();	
		
	}
	
	public Boolean validateToken(String token, String userEmail) {
		return extractUsername(token).equals(userEmail)&& !isTokenExpried(token);
	}
	
}
	
