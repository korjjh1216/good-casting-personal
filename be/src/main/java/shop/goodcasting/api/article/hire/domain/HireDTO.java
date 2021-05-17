package shop.goodcasting.api.article.hire.domain;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class HireDTO {
    private Long hiresId;
    private String hireTitle;
    private String cast;    //배역
    private String filming; //촬영기간
    private String guarantee; //출연료
    private String personnel; //모집인원
    private String deadline; //공고마감일
    private String contents; //내용
}
