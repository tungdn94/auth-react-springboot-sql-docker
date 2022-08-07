package tung.daongoc.jwtservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tung.daongoc.dto.user.Role;
import tung.daongoc.dto.user.UserInfoDto;
import tung.daongoc.jwtservice.config.TestConfig;
import tung.daongoc.jwtservice.dto.response.TestResponse;

import java.util.Arrays;

@RestController
@RequestMapping("/api/jwt")
@AllArgsConstructor
public class TestController {
	private TestConfig testConfig;
	
	@GetMapping("/test")
	public TestResponse testResponse(){
		return TestResponse.builder()
				.low(testConfig.getLow())
				.high(testConfig.getHigh())
				.build();
	}
	
	@GetMapping("/test-user")
	public UserInfoDto testUser(){
		return UserInfoDto.builder()
				.email("test@email.com")
				.userName("Test User")
				.roles(Arrays.asList(Role.USER, Role.MODERATOR))
				.build();
	}
}
