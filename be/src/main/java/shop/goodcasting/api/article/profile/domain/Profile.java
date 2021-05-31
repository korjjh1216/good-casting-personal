package shop.goodcasting.api.article.profile.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.user.actor.domain.Actor;

import javax.persistence.*;

@ToString(exclude = {"actor", "hires"})
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
    @Column private boolean privacy;
    @Column(columnDefinition = "TEXT") private String contents;
    @Column private String resemble;
    @Column private Double confidence;

    // join column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private Actor actor;


    public void changePrivacy(boolean privacy) {
        this.privacy = privacy;
    }
    public void changeContents(String contents) {
        this.contents = contents;
    }
    public void changeResemble(String resemble) {
        this.resemble = resemble;
    }
    public void changeConfidence(Double confidence) {
        this.confidence = confidence;
    }


    //temp
    @Column(columnDefinition = "TEXT") private String career;
    public void changeCareer(String career) {
        this.career = career;
    }
}