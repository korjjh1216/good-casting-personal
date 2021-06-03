package shop.goodcasting.api.article.profile.domain;

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
public class ProfilePageRequestDTO {
    private Integer page;
    private Integer size;
    private String sort;

    private String resembleKey;
    private Age age;
    private Height height;
    private Weight weight;
    private String genderKey;

    private Long actorId;

    private FileDTO file;

    public ProfilePageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
