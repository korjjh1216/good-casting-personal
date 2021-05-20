package shop.goodcasting.api.security.aop;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final SecurityProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("doFilterInternal : 진입");

        // header가 있는지 확인
        String token = provider.resolveToken(request);

        response.addHeader("Authorization", "Bearer " + token);
        System.out.println("token " + token);

        try {
            if(token != null && provider.validateToken(token)) {
                Authentication auth = provider.getAuthentication(token);
                System.out.println("auth : " + auth);
                System.out.println("auth.getDetails : " + auth.getDetails());

                // 시큐리티에 접근하여 auth객체를 저장
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println(auth);
            }
        } catch(SecurityRuntimeException e) {
            SecurityContextHolder.clearContext();
            response.sendError(e.getHttpStatus().value(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(filterChain);
        filterChain.doFilter(request, response);
        System.out.println("request" + request);
        System.out.println("response" + response);
        System.out.println("doFilterInternal : 끝");
    }
}