package shop.goodcasting.api.article.hire.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.file.domain.FileDTO;
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
    private String cast;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate filming;
    private Integer guarantee;
    private String personnel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime deadline;

    private ProducerDTO producer;
    private List<FileDTO> files;
}