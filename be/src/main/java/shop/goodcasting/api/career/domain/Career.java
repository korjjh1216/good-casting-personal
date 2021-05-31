package shop.goodcasting.api.career.domain;

import lombok.*;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.common.domain.BaseEntity;

import javax.persistence.*;

@ToString(exclude = {"profile"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "careers")
public class Career extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_id") private Long careerId;

    @Column private Integer year;
    @Column private String gerne;
    @Column private String title;
    @Column private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
