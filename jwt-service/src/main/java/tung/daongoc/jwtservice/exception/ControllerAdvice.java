package tung.daongoc.jwtservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tung.daongoc.dto.exception.UserNotFoundException;
import tung.daongoc.dto.response.SystemErrorResponse;
import tung.daongoc.dto.systemenum.SystemCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<SystemErrorResponse> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getAllErrors().stream().map(error ->
						SystemErrorResponse.builder()
								.systemCode(SystemCode.FIELD_CHECK_FAILED.getCodeNumber())
								.message(String.format("%s: %s", ((FieldError) error).getField(), error.getDefaultMessage()))
								.build())
				.collect(Collectors.toList());
	}
	
	@ExceptionHandler({LoginErrorException.class, UserNotFoundException.class, AuthenticationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public SystemErrorResponse handleLoginErrorException(
			Exception exception){
		log.error("{} : {}", exception.getClass(), exception.getMessage());
		return SystemErrorResponse.builder()
				.message(SystemCode.LOGIN_FAILED.getMessage())
				.systemCode(SystemCode.LOGIN_FAILED.getCodeNumber())
				.build();
	}
	
	@ExceptionHandler({UserInfoExistedException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public SystemErrorResponse handlerRegisterExceptions(
			Exception exception){
		log.error("{} : {}", exception.getClass(), exception.getMessage());
		return SystemErrorResponse.builder()
				.message(String.format("%s : %s", SystemCode.REGISTRATION_FAILED.getMessage(), exception.getMessage()))
				.systemCode(SystemCode.REGISTRATION_FAILED.getCodeNumber())
				.build();
	}
	
}
