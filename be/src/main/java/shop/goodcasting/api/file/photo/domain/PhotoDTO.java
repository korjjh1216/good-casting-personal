package shop.goodcasting.api.file.photo.domain;

import lombok.Data;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.article.profile.domain.Profile;

@Data
@Component
public class PhotoDTO {
    private Long photoId;
    private String fileSize;
    private String fileName;
    private String savedFileName;
    private Profile profile;
}
