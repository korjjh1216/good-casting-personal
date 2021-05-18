package shop.goodcasting.api.user.producer.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ProducerDTO {
    private Long producerId;
    private String email;
    private String agency;
    private String phone;
    private String position;
}
