package shop.goodcasting.api.user.actor.domain;

import lombok.*;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.user.login.domain.UserDTO;
import shop.goodcasting.api.user.login.domain.UserVO;

import java.sql.Timestamp;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO {
    private Long actorId;
    private String name;
    private String gender;
    private String birthday;
    private String phone;
    private String height;
    private String weight;
    private String agency;
    private boolean major;
    private Timestamp regDate;
    private Timestamp modDate;

    private UserDTO user;
}