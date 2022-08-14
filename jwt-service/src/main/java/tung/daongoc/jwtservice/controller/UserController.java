package tung.daongoc.jwtservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tung.daongoc.dto.systemenum.UserRole;
import tung.daongoc.dto.user.UserInfoDto;
import tung.daongoc.jwtservice.annotation.SwaggerShow;
import tung.daongoc.jwtservice.dto.request.LoginRequest;
import tung.daongoc.jwtservice.dto.request.RegisterRequest;
import tung.daongoc.jwtservice.dto.response.LoginResponse;
import tung.daongoc.jwtservice.dto.response.RegisterResponse;
import tung.daongoc.jwtservice.service.AccountService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;

@RestController
@SwaggerShow
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	private final AccountService accountService;
	
	@GetMapping("/test-user-mod")
	@PreAuthorize("hasAuthority('MODERATOR')")
	public UserInfoDto testUserModerator(){
		return UserInfoDto.builder()
				.email("mod@email.com")
				.userName("Test Moderator User")
				.userRoles(Arrays.asList(UserRole.USER, UserRole.MODERATOR))
				.build();
	}
	
	@GetMapping("/test-user-admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public UserInfoDto testUserAdmin(){
		return UserInfoDto.builder()
				.email("admin@email.com")
				.userName("Test Admin User")
				.userRoles(Arrays.asList(UserRole.USER, UserRole.ADMIN))
				.build();
	}
	
	@GetMapping("/test-user-normal")
	@PreAuthorize("hasAuthority('USER')")
	public UserInfoDto testUserNormal(){
		return UserInfoDto.builder()
				.email("user@email.com")
				.userName("Test User")
				.userRoles(Collections.singletonList(UserRole.USER))
				.build();
	}
}
