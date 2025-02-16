package univ.goormthon.kongju.member.exception;

import univ.goormthon.kongju.exception.KongjuException;
import univ.goormthon.kongju.exception.dto.ErrorCode;

public class MemberNotFoundException extends KongjuException {
    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
