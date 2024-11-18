package univ.goormthon.kongju.domain.parking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ.goormthon.kongju.domain.parking.repository.ParkingRepository;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;

}
