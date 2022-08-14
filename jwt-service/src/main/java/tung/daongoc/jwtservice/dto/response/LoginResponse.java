package tung.daongoc.jwtservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginResponse {
	private String message;
	private String jwtToken;
	private Integer code;
}
