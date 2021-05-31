package shop.goodcasting.api.file.domain;

import lombok.*;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.common.domain.BaseEntity;

import javax.persistence.*;

@ToString(exclude = {"profile", "hire"})
@Getter
@Table(name = "files")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileVO extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    @Column private String uuid;
    @Column private boolean first;
    @Column(name = "file_name") private String fileName;
    @Column(name = "photo_type") private boolean photoType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hire_id")
    private Hire hire;

    public void changeUuid(String uuid) {
        this.uuid = uuid;
    }

    public void changeFileName(String fileName) {
        this.fileName = fileName;
    }

    public void changeFirst(boolean first) {
        this.first = first;
    }
}
