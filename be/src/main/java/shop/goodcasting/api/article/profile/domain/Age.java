package shop.goodcasting.api.article.profile.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@Data
public class Age {
    private Integer from;
    private Integer to;
}
