package sondho.linkerly.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sondho.linkerly.exception.ExpiredUrlException;
import sondho.linkerly.exception.InvalidUrlException;
import sondho.linkerly.exception.UrlNotExistsException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UrlNotExistsException.class)
    public ResponseEntity<?> handlerUrlNotExistsException(UrlNotExistsException e) {
        log.error("Handling exception: " + e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(ExpiredUrlException.class)
    public ResponseEntity<?> handlerExpiredUrlException(ExpiredUrlException e) {
        log.error("Handling exception: " + e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<?> handleInvalidUrlException(InvalidUrlException e) {
        log.error("Handling exception: " + e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
