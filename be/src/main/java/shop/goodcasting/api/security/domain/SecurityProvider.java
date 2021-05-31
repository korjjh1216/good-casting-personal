package shop.goodcasting.api.security.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import shop.goodcasting.api.user.login.domain.Role;
import shop.goodcasting.api.user.login.service.UserDetailsServiceImpl;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class SecurityProvider implements AuthenticationProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000;

    private final UserDetailsServiceImpl service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        Date now = new Date();

        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        System.out.println("getAuthentication : 진입");
        UserDetails userDetails = service.loadUserByUsername(getUsername(token));
        System.out.println("userDetails : " + userDetails);
        System.out.println("userDetails.getAuth : " + userDetails.getAuthorities());
        // jwt토큰 서명을 통해 서명이 정상이라면 Authorities 객체 생성
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");


        log.info("bearerToken : " +bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        } else {
            return "No JWT token found in request headers";
        }
    }

    public boolean validateToken(String token) throws Exception {
        System.out.println("validateToken : 진입");
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            log.info(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token));
            log.info("validateToken : true");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("validateToken : false");
            throw new Exception();
        }
    }
}