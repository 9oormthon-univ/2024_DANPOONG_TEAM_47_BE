package univ.goormthon.kongju.global.exception;

import univ.goormthon.kongju.global.exception.dto.ErrorCode;

public class UploadFailedException extends KongjuException {
    public UploadFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
