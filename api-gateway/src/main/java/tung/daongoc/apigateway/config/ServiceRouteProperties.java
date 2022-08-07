package tung.daongoc.apigateway.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("service-name")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRouteProperties {
	private String jwtService;
}
