package tung.daongoc.jwtservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("test")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestConfig {
	private Integer low;
	private Integer high;
}
