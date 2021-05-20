package shop.goodcasting.api.mail.domain;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Data
@Component
public class MailDTO {
    private String address;
    private String title;
    private String msg;
    private MultipartFile file;
}