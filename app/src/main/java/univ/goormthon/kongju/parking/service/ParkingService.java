package univ.goormthon.kongju.parking.service;

import org.springframework.stereotype.Service;
import univ.goormthon.kongju.parking.domain.Parking;
import univ.goormthon.kongju.parking.utils.ParkingFinder;

import java.util.List;

@Service
public class ParkingService {

    private final ParkingFinder parkingFinder;

    public ParkingService(ParkingFinder parkingFinder) {
        this.parkingFinder = parkingFinder;
    }
    
    public List<Parking> getNearByParkings(Double latitude, Double longitude, Double radius) {
        return parkingFinder.getNearByParkings(latitude, longitude, radius);
    }

}
