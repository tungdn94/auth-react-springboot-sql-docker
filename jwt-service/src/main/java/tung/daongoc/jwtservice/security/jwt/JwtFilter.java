package tung.daongoc.jwtservice.security.jwt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import tung.daongoc.jwtservice.config.JwtConfig;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@Component("jwt-filter")
public class JwtFilter extends OncePerRequestFilter {
	
	@Qualifier("jwt-manager")
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtConfig jwtConfig;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var jwtToken = this.parseJwt(request);
		
		if (!Objects.isNull(jwtToken)) {
			var providedToken = new JwtAuthenticationToken(jwtToken, null);
			var validatedToken = authenticationManager.authenticate(providedToken);
			if (validatedToken.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(validatedToken);
			} else throw new InternalAuthenticationServiceException("JWT Token is invalid");
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String parseJwt(HttpServletRequest request) {
		var headerAuth = request.getHeader(jwtConfig.getHeader());
		
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(jwtConfig.getPrefix())) {
			return headerAuth.substring(jwtConfig.getPrefix().length());
		}
		
		return null;
	}
}
