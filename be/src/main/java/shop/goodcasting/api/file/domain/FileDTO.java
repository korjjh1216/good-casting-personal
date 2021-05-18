package shop.goodcasting.api.file.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.profile.domain.Profile;

@Data
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private Long fileId;
    private String fileName;
    private String uuid;
    private boolean first;
    private Profile profile;
    private Hire hire;
}