package univ.goormthon.kongju.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import univ.goormthon.kongju.global.exception.KongjuException;
import univ.goormthon.kongju.global.exception.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(KongjuException.class)
    public ResponseEntity<ErrorResponse> kongjuException(KongjuException e) {
        HttpStatus status = e.getErrorCode().getStatus();

        ErrorResponse response = ErrorResponse.builder()
            .status(status)
            .message(e.getErrorCode().getMessage())
            .validation(e.getValidation())
            .build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> invalidRequestHandler(MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.valueOf(e.getStatusCode().value());

        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .message("잘못된 요청입니다.")
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(response);
    }

}
