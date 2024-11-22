package univ.goormthon.kongju.domain.vehicle.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.goormthon.kongju.domain.vehicle.dto.request.VehicleRequest;
import univ.goormthon.kongju.domain.vehicle.dto.response.VehicleResponse;
import univ.goormthon.kongju.domain.vehicle.service.VehicleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kongju/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/list")
    public ResponseEntity<List<VehicleResponse>> getVehicles(HttpSession session) {
        return ResponseEntity.ok(vehicleService.getVehicles(session));
    }

    @PostMapping("/add")
    public ResponseEntity<VehicleResponse> addVehicle(HttpSession session,
                                                      @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.addVehicle(session, request));
    }

    @PutMapping("/update")
    public ResponseEntity<VehicleResponse> updateVehicle(HttpSession session,
                                                         @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.updateVehicle(session, request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteVehicle(HttpSession session,
                                              @RequestParam Long vehicleId) {
        vehicleService.deleteVehicle(session, vehicleId);
        return ResponseEntity.ok().build();
    }
}
