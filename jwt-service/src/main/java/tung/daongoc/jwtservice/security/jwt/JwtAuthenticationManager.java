package tung.daongoc.jwtservice.security.jwt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import tung.daongoc.jwtservice.exception.TokenNotSupportException;

@Component("jwt-manager")
public class JwtAuthenticationManager implements AuthenticationManager {
	
	@Qualifier("jwt-provider")
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!authenticationProvider.supports(authentication.getClass())) throw new TokenNotSupportException("Invalid authentication");
		return authenticationProvider.authenticate(authentication);
	}
}
