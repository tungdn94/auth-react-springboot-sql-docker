package tung.daongoc.jwtservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_info")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserEntity {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
	@SequenceGenerator(name = "user_seq_gen", sequenceName = "user_id_seq", initialValue = 101, allocationSize = 1)
	private Integer id;
	
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	@Column(name = "password", length = 255)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(  name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<RoleEntity> roleList;
}
