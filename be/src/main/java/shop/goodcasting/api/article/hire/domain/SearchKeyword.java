package shop.goodcasting.api.article.hire.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
@AllArgsConstructor
public class SearchKeyword {

    private String keyword;
    private boolean dummy;


}
