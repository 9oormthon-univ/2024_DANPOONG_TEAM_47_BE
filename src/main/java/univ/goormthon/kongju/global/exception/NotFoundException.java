package univ.goormthon.kongju.global.exception;

import univ.goormthon.kongju.global.exception.dto.ErrorCode;

public class NotFoundException extends KongjuException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
