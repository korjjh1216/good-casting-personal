package shop.goodcasting.api.article.hire.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.file.photo.domain.PhotoDTO;
import shop.goodcasting.api.file.video.domain.VideoDTO;
import shop.goodcasting.api.user.producer.domain.Producer;

import java.util.ArrayList;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class HireDTO {
    // pk
    private Long hireId;

    /// data
    private String hireTitle;
    private String cast;    //배역
    private String filming; //촬영기간
    private String guarantee; //출연료
    private String personnel; //모집인원
    private String deadline; //공고마감일
    private String contents; //내용

    //join object
    private Producer producer;
    private ArrayList<PhotoDTO> photos;
    private VideoDTO video;
    private ArrayList<ProfileDTO> profiles;
}
