package tung.daongoc.jwtservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tung.daongoc.dto.systemenum.UserRole;

import javax.servlet.Filter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	@Qualifier("jwt-filter")
	private Filter jwtFilter;
	
	@Autowired
	@Qualifier("authen-advice")
	private AuthenticationEntryPoint authenticationAdvice;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
			httpSecurity.csrf().disable().cors().disable();
			httpSecurity.exceptionHandling().authenticationEntryPoint(authenticationAdvice).
					and().authorizeRequests()
						.mvcMatchers("/api/account/**").permitAll()
						.mvcMatchers("/swagger-ui/**").permitAll()
						.mvcMatchers("/api/user/test-user-mod").hasAuthority(UserRole.MODERATOR.name())
						.mvcMatchers("/api/user/test-user-admin").hasAuthority(UserRole.ADMIN.name())
						.mvcMatchers("/api/user/test-user-normal").hasAuthority(UserRole.USER.name());

			httpSecurity.authorizeRequests().anyRequest().authenticated();
			httpSecurity.addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class);
			return httpSecurity.build();
	}
	

}
