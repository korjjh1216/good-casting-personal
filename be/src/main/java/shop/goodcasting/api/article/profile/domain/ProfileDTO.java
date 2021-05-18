package shop.goodcasting.api.article.profile.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.file.photo.domain.PhotoDTO;
import shop.goodcasting.api.file.video.domain.VideoDTO;
import shop.goodcasting.api.user.actor.domain.Actor;

import javax.persistence.Column;
import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ProfileDTO {
    // pk
    private long profileId;

    // data
    private boolean privacy;
    private String contents;
    private String career;
    private String resemble;
    private String confidence;

    // join object
    private Actor actor;
    private ArrayList<PhotoDTO> photos;
    private VideoDTO video;
    private ArrayList<HireDTO> hires;
}