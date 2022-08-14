package tung.daongoc.jwtservice.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenNotSupportException extends AuthenticationException {
	public TokenNotSupportException(String msg) {
		super(msg);
	}
}
