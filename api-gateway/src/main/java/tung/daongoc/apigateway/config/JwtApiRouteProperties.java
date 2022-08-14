package tung.daongoc.apigateway.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("api-route.jwt-service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtApiRouteProperties {
	private String account;
	private String user;
}
