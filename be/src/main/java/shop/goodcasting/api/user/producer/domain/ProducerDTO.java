package shop.goodcasting.api.user.producer.domain;

import lombok.*;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.user.login.domain.UserDTO;

import java.sql.Timestamp;

@Builder
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ProducerDTO {
    private Long producerId;
    private String email;
    private String agency; //회사이름
    private String phone;
    private String position;
    private String name;
    private Timestamp regDate;
    private Timestamp modDate;

    private UserDTO user;
}