package tung.daongoc.jwtservice.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import tung.daongoc.jwtservice.utility.JwtUtility;

@Component("jwt-provider")
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {
	@Qualifier("jwt-detail-service")
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtility jwtUtility;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var jwtToken = (String) authentication.getPrincipal();
		try {
			var userDetail = userDetailsService.loadUserByUsername(jwtUtility.getUsernameFromToken(jwtToken));
			return new JwtAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
		} catch (Exception exception){
			log.error("{} : {}", exception.getClass(), exception.getMessage());
			return authentication;
		}
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(JwtAuthenticationToken.class);
	}
}
