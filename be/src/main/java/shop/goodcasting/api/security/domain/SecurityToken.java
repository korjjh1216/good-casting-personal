package shop.goodcasting.api.security.domain;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log
public class SecurityToken {
    private final String token;
    private final String key;
    private int tokenExpirationMilliSec = 1800000;
    private static final String AUTHORITIES_KEY = "role";

    public SecurityToken(String key) {
        this.token = createToken();
        this.key = key;
    }

    public String createToken() {
        try {
            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");
            header.put("typ", "JWT");

            Map<String, Object> payload = new HashMap<>();
            payload.put("username", "");

            long expirationTime = 1000 * 60 * 60 * 2L;

            Date ext = new Date();
            ext.setTime(ext.getTime() + expirationTime);

            String token = Jwts
                    .builder()
                    .setHeader(header)
                    .setClaims(payload)
                    .setSubject("user") // 용도
                    .setExpiration(ext)
                    .signWith(SignatureAlgorithm.HS256, key.getBytes())
                    .compact();

            return token;
        }
        catch(SecurityException e) { log.info("Invalid JWT Signature: " + e); }
        catch(MalformedJwtException e) { log.info("Invalid JWT Token: " + e); }
        catch(ExpiredJwtException e) { log.info("Expired JWT Token: " + e); }
        catch(UnsupportedJwtException e) { log.info("Unsupported JWT Signature: " + e); }
        catch(IllegalArgumentException e) { log.info("JWT Token compact of handler are invalid: " + e); }

        return null;
    }
}