package tung.daongoc.jwtservice.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tung.daongoc.jwtservice.entity.RoleEntity;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntityDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<RoleEntityDAO> roleList;
}
