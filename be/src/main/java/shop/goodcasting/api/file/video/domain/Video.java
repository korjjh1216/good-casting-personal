package shop.goodcasting.api.file.video.domain;

import lombok.*;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.common.domain.BaseEntity;

import javax.persistence.*;

@Getter
@Table(name = "videos")
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long videoId;

    private String uuid;

    private boolean first;
    @Column(name = "file_name") private String fileName;


    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "hire_id")
    private Hire hire;
}
