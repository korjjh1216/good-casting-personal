package shop.goodcasting.api.article.profile.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.common.domain.HireProfile;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.user.actor.domain.Actor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column private String contents;
    @Column private String career;
    @Column private String resemble;
    @Column private String confidence;

    // join column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Builder.Default
    @OneToMany(mappedBy = "profile")
    private List<HireProfile> hires = new ArrayList<>();

    public void changePrivacy(boolean privacy) {
        this.privacy = privacy;
    }
    public void changeContents(String contents) {
        this.contents = contents;
    }
    public void changeCareer(String career) {
        this.career = career;
    }
    public void changeResemble(String resemble) {
        this.resemble = resemble;
    }
    public void changeConfidence(String confidence) {
        this.confidence = confidence;
    }
}