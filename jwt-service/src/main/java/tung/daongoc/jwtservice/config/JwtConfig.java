package tung.daongoc.jwtservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwt")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {
	private String header;
	private String prefix;
	private String secret;
	private Integer duration;
	private String issuer;
}
