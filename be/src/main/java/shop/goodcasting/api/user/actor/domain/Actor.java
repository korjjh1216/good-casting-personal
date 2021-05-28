package shop.goodcasting.api.user.actor.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.user.login.domain.UserVO;

import javax.persistence.*;

@ToString
@Builder
@Getter
@Entity
@Table(name = "actors")
@AllArgsConstructor
@NoArgsConstructor
public class Actor extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long actorId;

    @Column private String name;
    @Column private String gender;
    @Column private String birthday;
    @Column private String phone;
    @Column private Integer height;
    @Column private Integer weight;
    @Column private String agency;
    @Column private Boolean major;
    @Column private String email;
    @Column private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserVO user;

    public void changeUserVO(UserVO userVO) {
        this.user = userVO;
    }
}