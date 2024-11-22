package univ.goormthon.kongju.domain.vehicle.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "이동 수단", description = "이동 수단 관련 API")
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(summary = "이동 수단 목록 조회", description = "현재 로그인한 회원의 이동 수단 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이동 수단 목록 조회 성공", content = @Content(schema = @Schema(implementation = VehicleResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 세션이 존재하지 않음", content = @Content(schema = @Schema(implementation = VehicleResponse.class)))
    })
    @GetMapping("/list")
    public ResponseEntity<List<VehicleResponse>> getVehicles(HttpSession session) {
        return ResponseEntity.ok(vehicleService.getVehicles(session));
    }

    @Operation(summary = "이동 수단 추가", description = "현재 로그인한 회원의 이동 수단을 추가합니다.")
    @Parameters({
            @Parameter(name = "vehicle_type", description = "이동 수단 종류", required = true),
            @Parameter(name = "vehicle_number", description = "이동 수단 번호", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이동 수단 추가 성공", content = @Content(schema = @Schema(implementation = VehicleResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 세션이 존재하지 않음", content = @Content(schema = @Schema(implementation = VehicleResponse.class)))
    })
    @PostMapping("/add")
    public ResponseEntity<VehicleResponse> addVehicle(HttpSession session,
                                                      @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.addVehicle(session, request));
    }

    @Operation(summary = "이동 수단 수정", description = "현재 로그인한 회원의 이동 수단을 수정합니다.")
    @Parameters({
            @Parameter(name = "vehicle_type", description = "이동 수단 종류", required = true),
            @Parameter(name = "vehicle_number", description = "이동 수단 번호", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이동 수단 수정 성공", content = @Content(schema = @Schema(implementation = VehicleResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 세션이 존재하지 않음", content = @Content(schema = @Schema(implementation = VehicleResponse.class)))
    })
    @PutMapping("/update")
    public ResponseEntity<VehicleResponse> updateVehicle(HttpSession session,
                                                         @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.updateVehicle(session, request));
    }

    @Operation(summary = "이동 수단 삭제", description = "현재 로그인한 회원의 이동 수단을 삭제합니다.")
    @Parameters({
            @Parameter(name = "vehicle_id", description = "이동 수단 ID", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이동 수단 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "회원 세션이 존재하지 않음")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteVehicle(HttpSession session,
                                              @RequestParam Long vehicleId) {
        vehicleService.deleteVehicle(session, vehicleId);
        return ResponseEntity.ok().build();
    }
}
