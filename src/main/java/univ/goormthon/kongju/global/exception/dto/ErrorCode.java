package univ.goormthon.kongju.global.exception.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."), // 에러 코드 예시
    INVALID_DAY(HttpStatus.BAD_REQUEST, "잘못된 요일입니다."),
    FAILED_TO_RESERVE(HttpStatus.BAD_REQUEST, "예약에 실패했습니다.");

    private final HttpStatus status;
    private final String message;
}
