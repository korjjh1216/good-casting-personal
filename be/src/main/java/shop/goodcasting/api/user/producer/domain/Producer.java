package shop.goodcasting.api.user.producer.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.user.login.domain.UserVO;

import javax.persistence.*;

@ToString
@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="producers")
public class Producer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_id") private Long producerId;

    @Column private String email;
    @Column private String agency;
    @Column private String phone;
    @Column private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserVO userVO;

    public void changeUserVO(UserVO userVO) {
    }
}