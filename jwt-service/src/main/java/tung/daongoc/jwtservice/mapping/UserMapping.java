package tung.daongoc.jwtservice.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import tung.daongoc.jwtservice.dao.RoleEntityDAO;
import tung.daongoc.jwtservice.dao.UserEntityDAO;
import tung.daongoc.jwtservice.dto.request.RegisterRequest;
import tung.daongoc.jwtservice.entity.RoleEntity;
import tung.daongoc.jwtservice.entity.UserEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapping {
	UserEntityDAO mappingUserEntityDAO (UserEntity entity);
	
	RoleEntityDAO mappingRoleEntityDAO (RoleEntity entity);
	
	UserEntity mappingUserEntity(RegisterRequest request);
	
}
