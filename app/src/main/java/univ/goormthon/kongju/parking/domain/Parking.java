package univ.goormthon.kongju.parking.domain;

import lombok.Builder;
import lombok.Getter;
import univ.goormthon.kongju.entity.parking.ParkingEntity;

@Getter
public class Parking {

    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer carCapacity;
    private Integer pmCapacity;
    private String description;
    private Integer rate;

    @Builder
    public Parking(Long id, String name, String address, Double latitude, Double longitude, Integer carCapacity, Integer pmCapacity, String description, Integer rate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.carCapacity = carCapacity;
        this.pmCapacity = pmCapacity;
        this.description = description;
        this.rate = rate;
    }

    public static Parking of(ParkingEntity parking) {
        return Parking.builder()
                .id(parking.getId())
                .name(parking.getName())
                .address(parking.getAddress())
                .latitude(parking.getLatitude())
                .longitude(parking.getLongitude())
                .carCapacity(parking.getCarCapacity())
                .pmCapacity(parking.getPmCapacity())
                .description(parking.getDescription())
                .rate(parking.getRate())
                .build();
    }
}
