package tung.daongoc.dto.exception;

public class RoleNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public RoleNotFoundException() {
	}
	
	public RoleNotFoundException(String message) {
		super(message);
	}
}
