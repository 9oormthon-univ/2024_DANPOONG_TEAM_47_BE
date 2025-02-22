package univ.goormthon.kongju.parking.exception;

import univ.goormthon.kongju.exception.KongjuException;
import univ.goormthon.kongju.exception.dto.ErrorCode;

public class InvalidDataException extends KongjuException {
    public InvalidDataException(ErrorCode errorCode) {
        super(errorCode);
    }
}
