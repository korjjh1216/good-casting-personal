package shop.goodcasting.api.article.profile.domain;

import lombok.*;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.career.domain.CareerDTO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.user.actor.domain.ActorDTO;

import java.sql.Timestamp;
import java.util.List;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ProfileDTO {
    // pk
    private Long profileId;

    private boolean privacy;
    private String contents;
    private String resemble;
    private Double confidence;
    private String actorName;
    private Integer actorAge;

    private Timestamp regDate;
    private Timestamp modDate;

    private ActorDTO actor;

    private List<FileDTO> files;
    private List<CareerDTO> careers;
}