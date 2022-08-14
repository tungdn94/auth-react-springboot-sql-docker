package tung.daongoc.jwtservice.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tung.daongoc.dto.systemenum.UserRole;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntityDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String roleName;
}
