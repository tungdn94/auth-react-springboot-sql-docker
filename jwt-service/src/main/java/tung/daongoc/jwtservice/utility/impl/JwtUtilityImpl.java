package tung.daongoc.jwtservice.utility.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.crypto.generators.DESKeyGenerator;
import org.bouncycastle.jcajce.provider.asymmetric.dh.BCDHPrivateKey;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tung.daongoc.jwtservice.config.JwtConfig;
import tung.daongoc.jwtservice.dao.UserEntityDAO;
import tung.daongoc.jwtservice.exception.JwtTokenMalformedException;
import tung.daongoc.jwtservice.utility.JwtUtility;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
@Data
@RequiredArgsConstructor
public class JwtUtilityImpl implements JwtUtility {
	private final JwtConfig jwtConfig;
	private String USER_KEY = "usr";
	
	@Bean
	private Key getKey(){
		return new SecretKeySpec(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8),
				SignatureAlgorithm.HS512.getJcaName());
	}
	
	@Override
	public String generateToken(Authentication authentication) {
		var username = (String) authentication.getPrincipal();
		return Jwts.builder()
				.setIssuer(jwtConfig.getIssuer())
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtConfig.getDuration()))
				.signWith(getKey())
				.compact();
	}
	

	
	@Override
	public String getUsernameFromToken(String jwtToken) {
		var parser = Jwts.parserBuilder().setSigningKey(getKey()).build();
		return parser.parseClaimsJws(jwtToken).getBody().getSubject();
	}
	
}
