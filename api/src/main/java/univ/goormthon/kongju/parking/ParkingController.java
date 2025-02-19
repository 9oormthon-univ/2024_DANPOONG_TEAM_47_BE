package univ.goormthon.kongju.parking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import univ.goormthon.kongju.parking.domain.Parking;
import univ.goormthon.kongju.parking.dto.ParkingResponse;
import univ.goormthon.kongju.parking.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parkings")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<ParkingResponse>> getNearbyParkings(@RequestParam Double latitude,
                                                                   @RequestParam Double longitude,
                                                                   @RequestParam(defaultValue = "0.5") Double radius) {
        List<Parking> parkings = parkingService.getNearByParkings(latitude, longitude, radius);
        return ResponseEntity.ok(parkings.stream().map(ParkingResponse::of).toList());
    }
}
