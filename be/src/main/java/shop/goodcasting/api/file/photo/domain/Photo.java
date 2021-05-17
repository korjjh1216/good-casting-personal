package shop.goodcasting.api.file.photo.domain;

import lombok.Getter;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.common.domain.BaseEntity;

import javax.persistence.*;

@Getter
@Table(name = "photos")
@Entity
public class Photo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long photoId;

    @Column(name = "file_size") private String fileSize;
    @Column(name = "file_name") private String fileName;
    @Column(name = "saved_file_name") private String savedFileName;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
