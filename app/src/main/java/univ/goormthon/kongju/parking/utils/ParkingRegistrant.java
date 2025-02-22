package univ.goormthon.kongju.parking.utils;

import org.springframework.stereotype.Component;
import univ.goormthon.kongju.entity.parking.ParkingEntity;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.parking.domain.Parking;
import univ.goormthon.kongju.repository.ParkingRepository;

@Component
public class ParkingRegistrant {

    private final ParkingRepository parkingRepository;

    public ParkingRegistrant(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }


    public Parking registerParking(Member member, Parking request) {
        ParkingEntity parkingEntity = ParkingEntity.builder()
                .member(member.toEntity())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .address(request.getAddress())
                .name(request.getName())
                .carCapacity(request.getCarCapacity())
                .pmCapacity(request.getPmCapacity())
                .description(request.getDescription())
                .rate(request.getRate())
                .build();

        return Parking.of(parkingRepository.save(parkingEntity));
    }
}
