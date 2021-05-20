package shop.goodcasting.api.article.hire.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.common.domain.HireProfile;
import shop.goodcasting.api.user.producer.domain.Producer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = {"producer", "profiles"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "hires")
public class Hire extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hire_id") private Long hireId;

    // column
    @Column private String title;
    @Column private String project;
    @Column private String contents;
    @Column private String cast;    //배역
    @Column private String filming; //촬영기간
    @Column private String guarantee; //출연료
    @Column private String personnel; //모집인원
    @Column private String deadline; //공고마감일
    @Column private String confidence;
    @Column private String resemble;

    // join column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @Builder.Default
    @OneToMany(mappedBy = "hire")
    private List<HireProfile> profiles = new ArrayList<>();
}