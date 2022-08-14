package tung.daongoc.jwtservice.exception;

public class UserInfoExistedException extends RuntimeException{
	public UserInfoExistedException(String message) {
		super(message);
	}
}
