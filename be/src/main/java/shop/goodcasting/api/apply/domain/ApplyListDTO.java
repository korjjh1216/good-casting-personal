package shop.goodcasting.api.apply.domain;

import lombok.*;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;

import java.sql.Timestamp;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ApplyListDTO {
    // pk
    private Long applyId;
    // 1 : 지원, 2 : 지원취소, 3 : 컨택중, 4: 합격, 5: 불합격
    private Long applyStatus;


    private boolean privacy;
    private String actorName;
    private String title;
    private Timestamp regDate;
    private Timestamp modDate;

    private ProfileDTO profile;
    private HireDTO hire;
}

