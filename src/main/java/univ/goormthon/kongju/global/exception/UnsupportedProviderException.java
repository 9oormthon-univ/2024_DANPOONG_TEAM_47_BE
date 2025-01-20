package univ.goormthon.kongju.global.exception;

import univ.goormthon.kongju.global.exception.dto.ErrorCode;

public class UnsupportedProviderException extends KongjuException {
    public UnsupportedProviderException(ErrorCode errorCode) {
        super(errorCode);
    }
}
