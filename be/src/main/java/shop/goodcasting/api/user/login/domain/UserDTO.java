package shop.goodcasting.api.user.login.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String password;

    private List<Role> roles;

    private String token;
}
