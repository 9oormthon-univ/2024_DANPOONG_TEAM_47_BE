package univ.goormthon.kongju.global.exception;

import univ.goormthon.kongju.global.exception.dto.ErrorCode;

public class InvalidDayException extends KongjuException {
    public InvalidDayException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static class UnauthorizedException extends KongjuException {
        public UnauthorizedException(ErrorCode errorCode) {
            super(errorCode);
        }
    }
}
