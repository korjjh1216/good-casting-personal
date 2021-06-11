package shop.goodcasting.api.user.login.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @Column(unique = true) private String username;

    @Column private String password;
    @Column private boolean position;
    @Column private boolean account;
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeRoles(List<Role> roles) {
        this.roles = roles;
    }
}
