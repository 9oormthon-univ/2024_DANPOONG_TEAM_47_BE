package univ.goormthon.kongju.parking.utils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import univ.goormthon.kongju.parking.domain.Parking;
import univ.goormthon.kongju.repository.ParkingRepository;

import java.util.List;

@Component
public class ParkingFinder {

    private final ParkingRepository parkingRepository;

    public ParkingFinder(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional(readOnly = true)
    public List<Parking> getNearByParkings(Double latitude, Double longitude, Double radius) {
        return parkingRepository.findAllWithinRadius(latitude, longitude, radius).stream()
                .map(Parking::of)
                .toList();
    }

    public List<Parking> getMyParkings(Long memberId) {
        return parkingRepository.findAllByMemberId(memberId).stream()
                .map(Parking::of)
                .toList();
    }
}
