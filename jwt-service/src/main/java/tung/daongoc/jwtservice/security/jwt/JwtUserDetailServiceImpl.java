package tung.daongoc.jwtservice.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tung.daongoc.jwtservice.service.AccountService;

@Component("jwt-detail-service")
@RequiredArgsConstructor
public class JwtUserDetailServiceImpl implements UserDetailsService {
	private final AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountService.loadUserByUsername(username);
	}
}
