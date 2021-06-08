package shop.goodcasting.api.article.profile.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.user.actor.domain.Actor;

import javax.persistence.*;

@ToString(exclude = {"actor"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column private boolean privacy;
    @Column(columnDefinition = "TEXT") private String contents;
    @Column private String resemble;
    @Column private Double confidence;
    @Column private boolean first;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private Actor actor;
}
