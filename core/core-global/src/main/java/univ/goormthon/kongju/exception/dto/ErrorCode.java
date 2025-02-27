package univ.goormthon.kongju.exception.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    PARKING_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 주차장입니다."),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "주차장 이미지를 찾을 수 없습니다."),
    AVAILABILITY_NOT_FOUND(HttpStatus.NOT_FOUND, "날짜를 찾을 수 없습니다."),
    VEHICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "이동 수단을 찾을 수 없습니다."),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 예약정보입니다."),

    INVALID_DAY(HttpStatus.BAD_REQUEST, "잘못된 요일입니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력 값입니다."),
    FAILED_TO_RESERVE(HttpStatus.BAD_REQUEST, "예약에 실패했습니다."),
    FAILED_TO_UPLOAD(HttpStatus.BAD_REQUEST, "이미지 업로드에 실패했습니다."),
    UNSUPPORTED_PROVIDER(HttpStatus.BAD_REQUEST, "지원하지 않는 소셜 서비스 로그인입니다."),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "서비스 회원이 아닙니다.");

    private final HttpStatus status;
    private final String message;
}
