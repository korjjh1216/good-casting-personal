package shop.goodcasting.api.user.login.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    USER("ROLE_USER", "유저 권한"),
    UNKNOWN("ROLE_UNKNOWN", "알 수 없는 권한");

    private final String role;
    private final String message;

    public static Role of(String role) {
        return Arrays.stream(Role.values())
                .filter(i -> i.getRole().equals(role))
                .findAny()
                .orElse(UNKNOWN);
    }

    @Override
    public String getAuthority() {
        return name();
    }
}