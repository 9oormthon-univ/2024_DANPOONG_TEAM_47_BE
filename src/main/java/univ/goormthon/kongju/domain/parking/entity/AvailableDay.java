package univ.goormthon.kongju.domain.parking.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import univ.goormthon.kongju.global.exception.InvalidDayException;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum AvailableDay {

    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일"),
    SATURDAY("토요일"),
    SUNDAY("일요일");

    private final String day;

    public static AvailableDay of(String day) {
        for (AvailableDay availableDay : values()) {
            if (availableDay.day.equals(day)) {
                return availableDay;
            }
        }
        throw new InvalidDayException(ErrorCode.INVALID_DAY);
    }
}
