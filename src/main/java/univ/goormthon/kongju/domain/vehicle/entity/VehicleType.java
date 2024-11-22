package univ.goormthon.kongju.domain.vehicle.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import univ.goormthon.kongju.global.exception.InvalidDataException;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum VehicleType {

    CAR("자동차"),
    PM("개인형 이동장치");

    private final String type;

    public static VehicleType of(String type) {
        for (VehicleType vehicleType : values()) {
            if (vehicleType.type.equals(type)) {
                return vehicleType;
            }
        }
        throw new InvalidDataException(ErrorCode.INVALID_INPUT_VALUE);
    }
}
