package univ.goormthon.kongju.domain.parking.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import univ.goormthon.kongju.global.exception.InvalidDataException;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum AvailableDay {

    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private final String day;

    public static AvailableDay of(String day) {
        for (AvailableDay availableDay : values()) {
            if (availableDay.day.equals(day)) {
                return availableDay;
            }
        }
        throw new InvalidDataException(ErrorCode.INVALID_INPUT_VALUE);
    }
}
