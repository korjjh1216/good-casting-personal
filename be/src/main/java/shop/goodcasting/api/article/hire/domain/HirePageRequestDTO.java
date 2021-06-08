package shop.goodcasting.api.article.hire.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import shop.goodcasting.api.file.domain.FileDTO;

@ToString
@Builder
@AllArgsConstructor
@Data
public class HirePageRequestDTO {
    private Integer page;
    private Integer size;
    private String sort;

    private String searchKey;
    private Pay pay;
    private Period period;

    private Long producerId;

    private FileDTO file;

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
