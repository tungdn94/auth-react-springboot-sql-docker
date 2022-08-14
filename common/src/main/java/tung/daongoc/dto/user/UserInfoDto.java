package tung.daongoc.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tung.daongoc.dto.systemenum.UserRole;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto {
	private String userName;
	private String email;
	private List<UserRole> userRoles;
}
