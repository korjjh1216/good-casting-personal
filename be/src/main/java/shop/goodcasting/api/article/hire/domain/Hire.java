package shop.goodcasting.api.article.hire.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.user.producer.domain.Producer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString(exclude = {"producer"})
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

    @Column private String title;
    @Column private String project;
    @Column(columnDefinition = "TEXT") private String contents;
    @Column private String cast;    //배역
    @Column private LocalDate filming; //촬영기간
    @Column private Integer guarantee; //출연료
    @Column private String personnel; //모집인원
    @Column private LocalDateTime deadline; //공고마감일

    // join column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private Producer producer;

}