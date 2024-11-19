package univ.goormthon.kongju.global.exception.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public record ErrorResponse(HttpStatus status, String message, Map<String, String> validation) {

    @Builder
    public ErrorResponse(HttpStatus status, String message, Map<String, String> validation) {
        this.status = status;
        this.message = message;
        this.validation = validation != null ? validation : new HashMap<>();
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}