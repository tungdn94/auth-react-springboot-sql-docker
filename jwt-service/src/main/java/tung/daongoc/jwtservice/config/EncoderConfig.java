package tung.daongoc.jwtservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderConfig {
	@Bean(name = "bcrypt-encoder")
	public PasswordEncoder bcryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean(name = "noop-encoder")
	public PasswordEncoder noopPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
