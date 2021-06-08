package shop.goodcasting.api.apply.domain;

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
public class ApplyPageRequestDTO {
    private Integer page;
    private Integer size;
    private String sort;

    private Long actorId;
    private Long ProducerId;

    private FileDTO file;

    public ApplyPageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}