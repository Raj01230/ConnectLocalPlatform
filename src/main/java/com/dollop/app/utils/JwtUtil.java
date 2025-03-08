package com.dollop.app.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dollop.app.enums.OtpType;
import com.dollop.app.enums.TokenType;
import com.dollop.app.exception.ExpiredTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${app.secret}")
	private String secret;

//	Map<String, Object> claim = new HashMap<>();
//	claim.put("tokenType", "Auth_Token");
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	public String generateToken(String subject, OtpType otpType, TokenType tokenType) {
		Map<String, Object> m = new HashMap<String, Object>();
		if (tokenType.equals(TokenType.AUTH_TOKEN))
			m.put("otpType", otpType);
		m.put("tokenType", tokenType);
		return generateToken(m, subject, tokenType);
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			throw new ExpiredTokenException("===Token Expired");
		}
	}

	private String generateToken(Map<String, Object> claims, String subject, TokenType tokenType) {
		System.err.println(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(9)));
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(tokenType.equals(TokenType.AUTH_TOKEN)
						? new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5))
						: new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(9)))
				.setIssuer("ConnectLocalPlatfrom").signWith(SignatureAlgorithm.HS256, secret.getBytes()).compact();
	}

	public Object getHeader(String token, String key) {
		System.err.println(token);
		return this.getClaims(token).get(key);
	}

}
