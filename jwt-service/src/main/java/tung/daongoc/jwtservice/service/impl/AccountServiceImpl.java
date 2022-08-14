package tung.daongoc.jwtservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tung.daongoc.dto.exception.RoleNotFoundException;
import tung.daongoc.dto.exception.UserNotFoundException;
import tung.daongoc.dto.systemenum.SystemCode;
import tung.daongoc.dto.systemenum.UserRole;
import tung.daongoc.jwtservice.dto.request.LoginRequest;
import tung.daongoc.jwtservice.dto.request.RegisterRequest;
import tung.daongoc.jwtservice.dto.response.LoginResponse;
import tung.daongoc.jwtservice.dto.response.RegisterResponse;
import tung.daongoc.jwtservice.entity.UserEntity;
import tung.daongoc.jwtservice.exception.UserInfoExistedException;
import tung.daongoc.jwtservice.mapping.UserMapping;
import tung.daongoc.jwtservice.repository.RoleEntityRepository;
import tung.daongoc.jwtservice.repository.UserEntityRepository;
import tung.daongoc.jwtservice.security.UserDetailImpl;
import tung.daongoc.jwtservice.service.AccountService;
import tung.daongoc.jwtservice.utility.JwtUtility;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
	
	@Qualifier("bcrypt-encoder")
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;
	
	@Qualifier("noop-encoder")
	@Autowired
	private PasswordEncoder noopPasswordEncoder;
	
	@Autowired
	private UserEntityRepository userEntityRepository;
	@Autowired
	private RoleEntityRepository roleEntityRepository;
	
	@Autowired
	private UserMapping userMapping;
	@Autowired
	private JwtUtility jwtUtility;
	
	@Override
	public LoginResponse handlerLoginRequest(LoginRequest request) {
		var userEntity = userEntityRepository
				.findByUserNameOrEmail(request.getUserOrEmail(), request.getUserOrEmail()).orElseThrow(UserNotFoundException::new);
		
		if (!bcryptPasswordEncoder.matches(request.getPassword(), userEntity.getPassword())){
			if (!noopPasswordEncoder.matches(request.getPassword(), userEntity.getPassword())) throw new BadCredentialsException("Password is not match");
			userEntity = this.updateBCryptPassword(userEntity, bcryptPasswordEncoder);
    }
		
		var token = new UsernamePasswordAuthenticationToken(
				userEntity.getUserName(),	userEntity.getPassword(),
				userEntity.getRoleList().stream()
						.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
							.collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(token);
		
		return LoginResponse.builder()
					.code(SystemCode.LOGIN_SUCCESS.getCodeNumber())
					.message(SystemCode.LOGIN_SUCCESS.getMessage())
					.jwtToken(jwtUtility.generateToken(token))
				.build();
	}
	
	@Override
	public RegisterResponse handlerRegisterRequest(RegisterRequest request) {
		if (this.existedUsernameOrEmail(request.getUserName(), request.getEmail()))
			throw new UserInfoExistedException("Username or email is existed");

		var userEntity = this.saveUser(request, bcryptPasswordEncoder);
		var registerToken = new UsernamePasswordAuthenticationToken(userEntity.getUserName(), userEntity.getPassword(),
				userEntity.getRoleList().stream()
						.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
						.collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(registerToken);
		
		return RegisterResponse.builder()
				.message(String.format("User %s has been created", (String) registerToken.getCredentials()))
				.code(SystemCode.REGISTRATION_SUCCESS.getCodeNumber())
				.build();
	}
	
	@Override
	public UserDetailImpl loadUserByUsername(String username) {
		log.info(userEntityRepository.findByUserName(username).get().getRoleList().toString());
		return new UserDetailImpl(userMapping.mappingUserEntityDAO(userEntityRepository
				.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username))));
	}
	
	@Override
	public UserDetailImpl loadUserByUsernameOrEmail(String usernameOrEmail) {
		return new UserDetailImpl(userMapping.mappingUserEntityDAO(userEntityRepository
				.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail))));
	}
	
	@Override
	public UserEntity updateBCryptPassword(UserEntity userEntity, PasswordEncoder encoder) {
		userEntity.setPassword(encoder.encode(userEntity.getPassword()));
		return userEntityRepository.save(userEntity);
	}
	
	@Override
	public Boolean existedUsernameOrEmail(String username, String email) {
		return userEntityRepository.findByUserNameOrEmail(username, email).isPresent();
	}
	
	@Override
	public UserEntity saveUser(RegisterRequest request, PasswordEncoder encoder) {
		var newUser = userMapping.mappingUserEntity(request);
		newUser.setPassword(encoder.encode(request.getPassword()));
		newUser.setRoleList(Collections.singletonList(roleEntityRepository.findByRoleName(UserRole.USER.name()).orElseThrow(RoleNotFoundException::new)));
		return userEntityRepository.save(newUser);
	}
}
