package shop.goodcasting.api.article.hire.domain;

import lombok.*;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class HireDTO {
    private Long hireId;

    private String title;
    private String project;
    private String contents;
    private String cast;    //배역
    private LocalDate filming; //촬영기간
    private Integer guarantee; //출연료
    private String personnel; //모집인원
    private LocalDateTime deadline; //공고마감일

    private ProducerDTO producer;
    private List<FileDTO> files;
}