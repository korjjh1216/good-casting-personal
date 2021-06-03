package shop.goodcasting.api.article.hire.domain;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class HireListDTO {
    // pk
    private Long hireId;

    private String title;
    private String project;
    private String cast;
    private LocalDateTime deadline;
    private String personnel;
    private Timestamp regDate;
    private Timestamp modDate;

    private String fileName;
    private String fileUuid;
    private boolean first;

    private String producerAgency;
}