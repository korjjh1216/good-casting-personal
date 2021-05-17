package shop.goodcasting.api.file.video.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class VideoDTO {
    private Long videoId;
    private String fileSize;
    private String fileName;
    private String savedFileName;
}
