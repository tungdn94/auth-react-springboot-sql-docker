package tung.daongoc.jwtservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import tung.daongoc.jwtservice.dto.request.LoginRequest;
import tung.daongoc.jwtservice.dto.request.RegisterRequest;
import tung.daongoc.jwtservice.dto.response.LoginResponse;
import tung.daongoc.jwtservice.dto.response.RegisterResponse;
import tung.daongoc.jwtservice.entity.UserEntity;
import tung.daongoc.jwtservice.security.UserDetailImpl;

public interface AccountService {
	LoginResponse handlerLoginRequest (LoginRequest request);
	RegisterResponse handlerRegisterRequest (RegisterRequest request);
	
	UserDetailImpl loadUserByUsername(String username);
	UserDetailImpl loadUserByUsernameOrEmail(String usernameOrEmail);
	
	UserEntity updateBCryptPassword(UserEntity userEntity, PasswordEncoder encoder);
	
	Boolean existedUsernameOrEmail(String username, String email);
	
	UserEntity saveUser(RegisterRequest request, PasswordEncoder encoder);
}
