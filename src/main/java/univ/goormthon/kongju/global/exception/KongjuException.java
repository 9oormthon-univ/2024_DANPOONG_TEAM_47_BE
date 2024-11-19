package univ.goormthon.kongju.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public abstract class KongjuException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    private final ErrorCode errorCode;
}