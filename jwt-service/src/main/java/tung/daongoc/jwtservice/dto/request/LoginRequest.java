package tung.daongoc.jwtservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
	@NotBlank(message = "Username or Email must not be blank")
	private String userOrEmail;
	@NotBlank(message = "Password must not be blank")
	private String password;
}
