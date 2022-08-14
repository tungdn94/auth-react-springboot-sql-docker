package tung.daongoc.jwtservice.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tung.daongoc.jwtservice.dao.UserEntityDAO;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
public class UserDetailImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private final UserEntityDAO userEntityDAO;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userEntityDAO.getRoleList()
				.stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRoleName()))
				.collect(Collectors.toList());
	}
	
	@Override
	public String getPassword() {
		return userEntityDAO.getPassword();
	}
	
	@Override
	public String getUsername() {
		return userEntityDAO.getUserName();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	
	@Override
	public boolean isEnabled() {
		return false;
	}
}
