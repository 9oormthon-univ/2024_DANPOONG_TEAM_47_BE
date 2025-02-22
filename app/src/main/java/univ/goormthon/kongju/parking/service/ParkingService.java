package univ.goormthon.kongju.parking.service;

import org.springframework.stereotype.Service;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.parking.domain.Parking;
import univ.goormthon.kongju.parking.utils.ParkingFinder;
import univ.goormthon.kongju.parking.utils.ParkingRegistrant;

import java.util.List;

@Service
public class ParkingService {

    private final ParkingFinder parkingFinder;
    private final ParkingRegistrant parkingRegistrant;

    public ParkingService(ParkingFinder parkingFinder, ParkingRegistrant parkingRegistrant) {
        this.parkingFinder = parkingFinder;
        this.parkingRegistrant = parkingRegistrant;
    }
    
    public List<Parking> getNearByParkings(Double latitude, Double longitude, Double radius) {
        return parkingFinder.getNearByParkings(latitude, longitude, radius);
    }

    public List<Parking> getMyParkings(Member member) {
        return parkingFinder.getMyParkings(member.getId());
    }

    public Parking registerParking(Member member, Parking request) {
        return parkingRegistrant.registerParking(member, request);
    }
}
