package tung.daongoc.jwtservice.utility;

import org.springframework.security.core.Authentication;
import tung.daongoc.jwtservice.exception.JwtTokenMalformedException;


public interface JwtUtility {
	
	
	String generateToken(Authentication authentication);
	
	String getUsernameFromToken(String jwtToken);
	
}
