package univ.goormthon.kongju.domain.parking.controller;

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
import org.springframework.web.multipart.MultipartFile;
import univ.goormthon.kongju.domain.parking.dto.request.ParkingRegisterRequest;
import univ.goormthon.kongju.domain.parking.dto.response.ParkingRegisterResponse;
import univ.goormthon.kongju.domain.parking.entity.Parking;
import univ.goormthon.kongju.domain.parking.service.ParkingService;
import univ.goormthon.kongju.global.exception.NotFoundException;
import univ.goormthon.kongju.global.exception.dto.ErrorResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kongju/parking")
@Tag(name = "Parking", description = "주차장 API")
public class ParkingController {

    private final ParkingService parkingService;

    @Operation(summary = "주변 주차장 조회", description = "지도 중심 좌표를 기준으로 반경 500m 내의 공유 주차장을 표시합니다.")
    @Parameters(value = {
            @Parameter(name = "latitude", description = "위도"),
            @Parameter(name = "longitude", description = "경도"),
            @Parameter(name = "radius", description = "반경 (기본값: 0.5km)")
    })
    @ApiResponse(responseCode = "200", description = "주차장 조회 성공", content = @Content(schema = @Schema(implementation = Parking.class)))
    @GetMapping("/nearby")
    public ResponseEntity<List<Parking>> getNearbyParkings(@RequestParam Double latitude,
                                                           @RequestParam Double longitude,
                                                           @RequestParam(defaultValue = "0.5") Double radius) {
        return ResponseEntity.ok(parkingService.getNearbyParkings(latitude, longitude, radius));
    }

    @Operation(summary = "주차장 등록", description = "주차장을 등록합니다.")
    @Parameters(value = {
            @Parameter(name = "request", description = "주차장 등록 요청 JSON 형식의 데이터"),
            @Parameter(name = "images", description = "주차장 이미지 파일")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "주차장 등록 성공", content = @Content(schema = @Schema(implementation = ParkingRegisterResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 세션이 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "이미지 업로드 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<ParkingRegisterResponse> registerParking(
            HttpSession session,
            @RequestPart("request") ParkingRegisterRequest request,
            @RequestPart("images") List<MultipartFile> images) {
        request.setImages(images);
        Parking parking = parkingService.registerParking(session, request);
        return ResponseEntity.ok(parkingService.EntitytoDto(parking));
    }

    @Operation(summary = "주차장 수정", description = "주차장 정보를 수정합니다.")
    @Parameters(value = {
            @Parameter(name = "parkingId", description = "주차장 ID"),
            @Parameter(name = "request", description = "주차장 수정 요청 JSON 형식의 데이터"),
            @Parameter(name = "images", description = "주차장 이미지 파일")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "주차장 수정 성공", content = @Content(schema = @Schema(implementation = ParkingRegisterResponse.class))),
            @ApiResponse(responseCode = "400", description = "이미지 업로드 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 세션이 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "주차장을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/update")
    public ResponseEntity<ParkingRegisterResponse> updateParking(HttpSession session, @RequestParam Long parkingId,
                                                 @RequestPart("request") ParkingRegisterRequest request,
                                                 @RequestPart("images") List<MultipartFile> images) {
        request.setImages(images);
        Parking updatedParking = parkingService.updateParking(session, parkingId, request);
        return ResponseEntity.ok(parkingService.EntitytoDto(updatedParking));
    }

    @Operation(summary = "주차장 삭제", description = "주차장을 삭제합니다.")
    @Parameters(value = {
            @Parameter(name = "parkingId", description = "주차장 ID")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "주차장 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "회원 세션이 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "주차장을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteParking(HttpSession session, @RequestParam Long parkingId) {
        parkingService.deleteParking(session, parkingId);
        return ResponseEntity.noContent().build();
    }
}
