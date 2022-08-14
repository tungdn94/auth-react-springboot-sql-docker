package tung.daongoc.jwtservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tung.daongoc.dto.systemenum.UserRole;
import tung.daongoc.jwtservice.entity.RoleEntity;

import java.util.Optional;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {
	Optional<RoleEntity> findByRoleName(String roleName);
}
