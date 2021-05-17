package shop.goodcasting.api.user.producer.domain;

import lombok.Getter;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.user.login.domain.UserVO;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "producers")
public class Producer extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_id") private Long producerId;

    @Column private String password;
    @Column private String email;
    @Column private String agency;
    @Column private String phone;
    @Column private String position;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserVO userVO;
}
