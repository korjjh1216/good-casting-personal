package shop.goodcasting.api.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.domain.FileVO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private Integer page;
    private Integer size;
    private String type;
    private String sort;

    private SearchConditionDTO searchCond;

    private Long actorId;
    private Long producerId;

    private FileDTO file;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}