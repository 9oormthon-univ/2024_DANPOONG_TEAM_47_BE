package univ.goormthon.kongju.domain.reservation.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import univ.goormthon.kongju.global.exception.ReservationFailedException;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum ReservationStatus {

        WAITING("대기중"),
        ACCEPTED("승인됨"),
        REJECTED("거절됨"),
        CANCELED("취소됨");

        private final String status;

        public static ReservationStatus of(String status) {
            for (ReservationStatus reservationStatus : values()) {
                if (reservationStatus.status.equals(status)) {
                    return reservationStatus;
                }
            }
            throw new ReservationFailedException(ErrorCode.FAILED_TO_RESERVE);
        }
}
