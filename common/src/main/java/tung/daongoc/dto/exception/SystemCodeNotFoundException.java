package tung.daongoc.dto.exception;

public class SystemCodeNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public SystemCodeNotFoundException() {
	}
	
	public SystemCodeNotFoundException(String message) {
		super(message);
	}
}
