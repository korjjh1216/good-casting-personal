package shop.goodcasting.api.security.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.goodcasting.api.security.domain.Messenger;
import shop.goodcasting.api.security.exception.ErrorCode;
import shop.goodcasting.api.security.exception.LoginRuntimeException;

@Slf4j
@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Messenger> handleRuntimeException(RuntimeException e) {
        log.info("handleRuntimeException: ", e);

        Messenger response = Messenger.builder()
                .code("test")
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SecurityException.class)
    protected ResponseEntity<Messenger> handleSecurityException(SecurityException e) {
        log.info("handleSecurityException: ", e);

        Messenger response = Messenger.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(e.getMessage())
                .status(ErrorCode.AUTHENTICATION_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LoginRuntimeException.class)
    protected ResponseEntity<Messenger> handleLoginRuntimeException(LoginRuntimeException e) {
        log.info("handleLoginRuntimeException: ", e);

        Messenger response = Messenger.builder()
                .code(ErrorCode.LOGIN_FAILED.getCode())
                .message(e.getMessage())
                .status(ErrorCode.LOGIN_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Messenger> handleBadCredentialsException(BadCredentialsException e) {
        log.info("handleBadCredentialsException: ", e);

        Messenger response = Messenger.builder()
                .code(ErrorCode.LOGIN_FAILED.getCode())
                .message(e.getMessage())
                .status(ErrorCode.LOGIN_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Messenger> handleAccessDeniedException(AccessDeniedException e) {
        log.info("handleAccessDeniedException: ", e);

        Messenger response = Messenger.builder()
                .code(ErrorCode.ACCESS_FAILED.getCode())
                .message(ErrorCode.ACCESS_FAILED.getMessage())
                .status(ErrorCode.ACCESS_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    protected ResponseEntity<Messenger> handleInsufficientAuthenticationException(InsufficientAuthenticationException e) {
        log.info("handleInsufficientAuthenticationException: ", e);

        Messenger response = Messenger.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(ErrorCode.AUTHENTICATION_FAILED.getMessage())
                .status(ErrorCode.AUTHENTICATION_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}