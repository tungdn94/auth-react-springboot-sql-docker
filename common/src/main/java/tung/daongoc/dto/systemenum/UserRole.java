package tung.daongoc.dto.systemenum;

import tung.daongoc.dto.exception.RoleNotFoundException;

import java.util.stream.Stream;

public enum UserRole {
	UNREGISTERED(-1),
	ADMIN(0),
	MODERATOR(1),
	USER(2);
	
	private Integer roleNumber;
	
	UserRole(Integer roleNumber) {
	}
	
	public Integer getRoleNumber(){
		return roleNumber;
	}
	
	public static UserRole getRole(Integer roleNumber) {
		return Stream.of(UserRole.values())
				.filter(p -> p.getRoleNumber() == roleNumber)
				.findFirst().orElseThrow(RoleNotFoundException::new);
	}

}
