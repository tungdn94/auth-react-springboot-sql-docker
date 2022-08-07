package tung.daongoc.jwtservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tung.daongoc.jwtservice.config.TestConfig;
import tung.daongoc.jwtservice.dto.response.TestResponse;

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
}
