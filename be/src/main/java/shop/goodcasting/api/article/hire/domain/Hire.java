package shop.goodcasting.api.article.hire.domain;

import lombok.Getter;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.common.domain.HireProfile;
import shop.goodcasting.api.user.producer.domain.Producer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "hires")
public class Hire extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hire_id") private Long hireId;

    @Column private String title;
    @Column private String contents;
    @Column private String cast;    //배역
    @Column private String filming; //촬영기간
    @Column private String guarantee; //출연료
    @Column private String personnel; //모집인원
    @Column private String deadline; //공고마감일

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @OneToMany(mappedBy = "hire")
    private List<HireProfile> profiles = new ArrayList<>();
}