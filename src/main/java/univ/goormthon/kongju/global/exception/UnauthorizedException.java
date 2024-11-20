package univ.goormthon.kongju.global.exception;

import univ.goormthon.kongju.global.exception.dto.ErrorCode;

public class UnauthorizedException extends KongjuException {
    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
