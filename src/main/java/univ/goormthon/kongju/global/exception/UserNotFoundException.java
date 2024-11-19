package univ.goormthon.kongju.global.exception;

import univ.goormthon.kongju.global.exception.dto.ErrorCode;

public class UserNotFoundException extends KongjuException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
