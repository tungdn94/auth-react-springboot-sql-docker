package tung.daongoc.dto.systemenum;

import tung.daongoc.dto.exception.RoleNotFoundException;
import tung.daongoc.dto.exception.SystemCodeNotFoundException;

import java.util.stream.Stream;

public enum SystemCode {
	LOGIN_SUCCESS(1000, "Login Success"),
	REGISTRATION_SUCCESS(1001, "Registration success"),
	AUTHENTICATION_SUCCESS(1002, "Authentication success"),
	
	LOGIN_FAILED(1100, "Login failed"),
	REGISTRATION_FAILED(1101, "Registration failed"),
	AUTHENTICATION_FAILED(1102, "Authentication failed"),
	FIELD_CHECK_FAILED(1103, "Field error"),
	;
	
	
	
	
	private final Integer codeNumber;
	private final String message;
	
	SystemCode(Integer codeNumber, String message) {
		this.codeNumber = codeNumber;
		this.message = message;
	}
	
	public Integer getCodeNumber(){
		return codeNumber;
	}
	public String getMessage(){
		return message;
	}
	}
