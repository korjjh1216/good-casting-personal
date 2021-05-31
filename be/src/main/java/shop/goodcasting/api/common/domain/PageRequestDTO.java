package shop.goodcasting.api.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

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

    private String rkeyword;
    private Integer hfrom;
    private Integer hto;
    private Integer afrom;
    private Integer ato;
    private String gkeyword;
    private Integer wfrom;
    private Integer wto;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ffrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fto;
    private String conKeyword;
    private String castKeyword;
    private Integer gfrom;
    private Integer gto;
    private String tkeyword;
    private String pkeyword;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}