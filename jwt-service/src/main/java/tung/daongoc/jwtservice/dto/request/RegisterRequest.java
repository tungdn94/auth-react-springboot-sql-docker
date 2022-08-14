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
public class RegisterRequest {
	@NotBlank(message = "Username can not be blank")
	private String userName;
	@NotBlank(message = "Firstname can not be blank")
	private String firstName;
	@NotBlank(message = "Lastname can not be blank")
		private String lastName;
	@NotBlank(message = "Email can not be blank")
	private String email;
	@NotBlank(message = "Password can not be blank")
	private String password;
}
