package shop.goodcasting.api.article.profile.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.common.domain.HireProfile;
import shop.goodcasting.api.user.actor.domain.Actor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {
    // pk
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    // column
    @Column private Boolean privacy;
    @Column private String contents;
    @Column private String career;
    @Column private String resemble;
    @Column private String confidence;

    // join column
    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @OneToMany(mappedBy = "profile")
    private List<HireProfile> hires = new ArrayList<>();
}