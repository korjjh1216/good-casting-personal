package shop.goodcasting.api.user.actor.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.user.login.domain.UserVO;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "actors")
public class Actor extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long actorId;

    @Column private String name;
    @Column private String gender;
    @Column private String birthday;
    @Column private String phone;
    @Column private String authority;
    @Column private String height;
    @Column private String weight;
    @Column private String agency;
    @Column private Boolean major;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserVO userVO;

    public void changeActorId(Long actorId) {
        this.actorId = actorId;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeHeight(String height) {
        this.height = height;
    }

    public void changeWeight(String weight) {
        this.weight = weight;
    }

    public void changeAgency(String agency) {
        this.agency = agency;
    }

    public void changeMajor(Boolean major) {
        this.major = major;
    }

    public void changeUserVO(UserVO userVO) {
        this.userVO = userVO;
    }
}
