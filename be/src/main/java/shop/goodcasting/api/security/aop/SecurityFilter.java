package shop.goodcasting.api.security.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import shop.goodcasting.api.security.domain.SecurityProvider;
import shop.goodcasting.api.security.exception.SecurityRuntimeException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final SecurityProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("doFilterInternal : 진입");

        // header가 있는지 확인

        log.info(" " + request);
        String token = provider.resolveToken(request);

        response.addHeader("Authorization", "Bearer " + token);
        log.info("response : "+ response);
        log.info("token : " + token);

        try {
            if(token != null && provider.validateToken(token)) {
                Authentication auth = provider.getAuthentication(token);
                log.info("auth : " + auth);
                log.info("auth.getDetails : " + auth.getDetails());

                // 시큐리티에 접근하여 auth객체를 저장
                SecurityContextHolder.getContext().setAuthentication(auth);
                log .info("auth : " + auth);
            }
        } catch(SecurityRuntimeException e) {
            SecurityContextHolder.clearContext();
            response.sendError(e.getHttpStatus().value(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }

        log.info("filterChain : " + filterChain);
        filterChain.doFilter(request, response);

        log.info("request : " + request);
        log.info("response" + response);
        log.info("doFilterInternal : 끝");
    }
}