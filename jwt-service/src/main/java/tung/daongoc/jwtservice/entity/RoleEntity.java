package tung.daongoc.jwtservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import tung.daongoc.dto.systemenum.UserRole;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class RoleEntity {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq_gen")
	@SequenceGenerator(name = "role_seq_gen", sequenceName = "role_id_seq", initialValue = 3, allocationSize = 1)
	private Integer id;
	
	private String roleName;
	
	private Integer roleCode;
}
