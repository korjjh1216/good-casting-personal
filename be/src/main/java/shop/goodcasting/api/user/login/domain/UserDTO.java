package shop.goodcasting.api.user.login.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private boolean position;
    private boolean account;
    private List<Role> roles;

    private String token;
}