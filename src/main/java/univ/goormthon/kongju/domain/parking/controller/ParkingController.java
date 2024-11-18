package univ.goormthon.kongju.domain.parking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.goormthon.kongju.domain.parking.service.ParkingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kongju/parking")
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/create")
    public ResponseEntity<?> createParking() {
        return ResponseEntity.ok(parkingService.createParking());
    }

    // 내 주차장 조회
    @GetMapping("/get")
    public ResponseEntity<?> getMyParking() {
        return ResponseEntity.ok(parkingService.getMyParking());
    }

    /**
     * 주차장 조회
     * 지도 중심 반경 500m 이내의 모든 주차장 조회
     * @return List
     */
    @GetMapping("/list")
    public ResponseEntity<?> getParking() {
        return ResponseEntity.ok(parkingService.getParking());
    }

    // 내 주차장 수정
    @PutMapping("/update")
    public ResponseEntity<?> updateMyParking() {
        return ResponseEntity.ok(parkingService.updateMyParking());
    }

    // 내 주차장 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMyParking() {
        return ResponseEntity.ok(parkingService.deleteMyParking());
    }
}
