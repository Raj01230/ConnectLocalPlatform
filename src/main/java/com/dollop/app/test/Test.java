package com.dollop.app.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class Test {
	
	
	public static void main(String[] args) {
		String  secret ="SAMPLE" ;
		
//1. Generate Token
		Map<String, Object> claim = new HashMap<>();
		claim.put("tokenType", "Auth_Token");
		String token = Jwts.builder()
				.setClaims(claim)
				.setId("123456")
				.setSubject("patidarraj531@gmail.com")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
				.setIssuer("Admin")
				.signWith(SignatureAlgorithm.HS256, secret.getBytes())
				.compact();
		
		System.out.println(token);
		
//2. Claim Token
		
		Claims c = Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXRpZGFycmFqNTMxQGdtYWlsLmNvbSIsImlzcyI6IkNvbm5lY3RMb2NhbFBsYXRmcm9tIiwidG9rZW5UeXBlIjoiQVVUSF9UT0tFTiIsImV4cCI6MTc0MTE1NTI2MCwib3RwVHlwZSI6IkxPR0lOIiwiaWF0IjoxNzQxMTU1MjYwfQ.iexXo31qkriaVG6SnJS-y9QyieX6OAl0kB4D7fbc5Js"
						+ "").getBody();
		
//		
//		System.out.println(c.getSubject());
//		System.out.println(c.getIssuer());
//		System.out.println(c.get());
		System.out.println(c.getExpiration());

	}

}
