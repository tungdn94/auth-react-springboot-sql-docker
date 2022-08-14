package tung.daongoc.jwtservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tung.daongoc.dto.systemenum.SystemCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component("authen-advice")
@Slf4j
public class AuthenticationAdvice implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		log.error("Unauthorized error: {}", authException.getMessage());
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		final var body = new HashMap<String, Object>();
		body.put("status", SystemCode.AUTHENTICATION_FAILED.getCodeNumber());
		body.put("error", SystemCode.AUTHENTICATION_FAILED.getMessage());
		body.put("path", request.getServletPath());
		
		final var mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
	}
}
