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
    private String agency;
    private String phone;
    private String position;

    private UserDTO user;
}