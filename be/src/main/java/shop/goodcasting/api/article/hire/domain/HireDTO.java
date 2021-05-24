package shop.goodcasting.api.article.hire.domain;


import lombok.*;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class HireDTO {
    // pk
    private Long hireId;

    /// data
    private String title;
    private String project;
    private String cast;    //배역
    private String filming; //촬영기간
    private String guarantee; //출연료
    private String personnel; //모집인원
    private String deadline; //공고마감일
    private String contents; //내용
    private Double confidence;
    private String resemble;
    private Timestamp regDate;
    private Timestamp modDate;

    private String producerName;
    private String fileUuid;
    private String fileName;

    //join object
    private ProducerDTO producer;
    private List<FileDTO> files;
}