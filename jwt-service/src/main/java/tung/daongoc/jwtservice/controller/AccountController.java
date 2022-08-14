package tung.daongoc.jwtservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tung.daongoc.jwtservice.config.TestConfig;
import tung.daongoc.jwtservice.dto.request.LoginRequest;
import tung.daongoc.jwtservice.dto.request.RegisterRequest;
import tung.daongoc.jwtservice.dto.response.LoginResponse;
import tung.daongoc.jwtservice.dto.response.RegisterResponse;
import tung.daongoc.jwtservice.dto.response.TestResponse;
import tung.daongoc.jwtservice.service.AccountService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
	private final TestConfig testConfig;
	
	@GetMapping("/test-config")
	public TestResponse testResponse(){
		return TestResponse.builder()
				.low(testConfig.getLow())
				.high(testConfig.getHigh())
				.build();
	}
	
	
	private final AccountService accountService;
	
	@PostMapping("/register")
	public RegisterResponse registerPost(@Valid RegisterRequest request){
		return accountService.handlerRegisterRequest(request);
	}
	
	@PostMapping("/login")
	public LoginResponse loginPost(@Valid LoginRequest request){
		return accountService.handlerLoginRequest(request);
	}
	
}
