package univ.goormthon.kongju.domain.parking.dto.response;

import univ.goormthon.kongju.domain.parking.entity.Parking;
import univ.goormthon.kongju.domain.parking.entity.ParkingAvailability;
import univ.goormthon.kongju.domain.parking.entity.ParkingImage;

import java.util.List;

public record ParkingResponse(
        Parking parking,
        List<ParkingAvailability> availabilities,
        List<ParkingImage> images
) {
}
