package shop.goodcasting.api.file.photo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.article.profile.domain.Profile;

@Data
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDTO {
    private Long photoId;
    private String fileName;
    private String uuid;
    private boolean first;
    private Profile profile;
}
