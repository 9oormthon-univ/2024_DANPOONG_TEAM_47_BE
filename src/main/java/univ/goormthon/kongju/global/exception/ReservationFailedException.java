package univ.goormthon.kongju.global.exception;

import univ.goormthon.kongju.global.exception.dto.ErrorCode;

public class ReservationFailedException extends KongjuException {
    public ReservationFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
