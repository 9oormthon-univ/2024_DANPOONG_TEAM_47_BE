package univ.goormthon.kongju.exception;

import univ.goormthon.kongju.exception.dto.ErrorCode;

public class UnsupportedProviderException extends KongjuException {

    public UnsupportedProviderException(ErrorCode errorCode) {
        super(errorCode);
    }
}
