package shop.goodcasting.api.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@ToString
@Builder
@AllArgsConstructor
@Data
public class SearchConditionDTO {
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
}