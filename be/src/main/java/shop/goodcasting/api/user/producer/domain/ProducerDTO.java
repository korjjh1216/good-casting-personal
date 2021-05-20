package shop.goodcasting.api.user.producer.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@Component
public class ProducerDTO {
    private Long producerId;
    private String email;
    private String agency;
    private String phone;
    private String position;
}