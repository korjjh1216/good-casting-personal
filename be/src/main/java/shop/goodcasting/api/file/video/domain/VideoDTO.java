package shop.goodcasting.api.file.video.domain;

import lombok.*;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.profile.domain.Profile;

@Data
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO {
    private Long videoId;
    private String fileName;
    private String uuid;
    private boolean first;
    private Profile profile;
    private Hire hire;
}
