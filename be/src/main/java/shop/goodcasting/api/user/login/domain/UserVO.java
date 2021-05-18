package shop.goodcasting.api.user.login.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;

import javax.persistence.*;
import java.util.List;

@ToString
@Builder
@Getter
@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") private Long userId;
    @Column(unique = true, nullable = false) private String username;
    @Column(nullable = false) private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;
}
