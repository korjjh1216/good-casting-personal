package shop.goodcasting.api.user.actor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ActorDTO {
    private Long actorId;
    private String name;
    private String gender;
    private String birthday;
    private String phone;
    private String authority;
    private String height;
    private String weight;
    private String agency;
    private Boolean major;
}
