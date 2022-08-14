package tung.daongoc.jwtservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tung.daongoc.jwtservice.entity.UserEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
	
	Optional<UserEntity> findByUserName(String username);
	Optional<UserEntity> findByUserNameOrEmail(String username, String email);
}
