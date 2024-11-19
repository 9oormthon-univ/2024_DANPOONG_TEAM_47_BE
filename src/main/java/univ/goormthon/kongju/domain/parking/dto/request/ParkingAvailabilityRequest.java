package univ.goormthon.kongju.domain.parking.dto.request;

public record ParkingAvailabilityRequest(
        String day,
        String startTime,
        String endTime
) {}
