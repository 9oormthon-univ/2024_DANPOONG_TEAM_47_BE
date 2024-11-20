package univ.goormthon.kongju.domain.parking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ParkingAvailabilityRequest(
        String day,

        @JsonProperty("start_time") String startTime,
        @JsonProperty("end_time") String endTime
) {}
