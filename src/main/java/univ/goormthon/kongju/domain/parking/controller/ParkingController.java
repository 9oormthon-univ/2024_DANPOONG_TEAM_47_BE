package univ.goormthon.kongju.domain.parking.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import univ.goormthon.kongju.domain.parking.dto.request.ParkingRegisterRequest;
import univ.goormthon.kongju.domain.parking.entity.Parking;
import univ.goormthon.kongju.domain.parking.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/api/kongju/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;
    @PostMapping("/register")
    public ResponseEntity<Parking> registerParking(
            HttpSession session,
            @RequestPart("request") ParkingRegisterRequest request,
            @RequestPart("images") List<MultipartFile> images) {
        request.setImages(images);
        Parking parking = parkingService.registerParking(session, request);
        return ResponseEntity.ok(parking);
    }

    @PutMapping("/update")
    public ResponseEntity<Parking> updateParking(HttpSession session, @RequestParam Long parkingId, @RequestBody ParkingRegisterRequest request) {
        Parking updatedParking = parkingService.updateParking(session, parkingId, request);
        return ResponseEntity.ok(updatedParking);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteParking(HttpSession session, @RequestParam Long parkingId) {
        parkingService.deleteParking(session, parkingId);
        return ResponseEntity.noContent().build();
    }
}
