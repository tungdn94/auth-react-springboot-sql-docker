package tung.daongoc.apigateway.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ApiGatewayConfiguration {
	private ApiRouteProperties apiRouteProperties;
	private ServiceRouteProperties serviceProperties;
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
		return builder.routes()
				.route("jwt",
						p -> p.path(patternURI(apiRouteProperties.getJwtService()))
						.uri(loadBalancingURI(serviceProperties.getJwtService())))
				.build();
	}
	
	private String patternURI(String input){
		return String.format("%s/**", input);
	}
	
	private String loadBalancingURI(String input){
		return String.format("lb://%s", input);
	}
	
	
}
