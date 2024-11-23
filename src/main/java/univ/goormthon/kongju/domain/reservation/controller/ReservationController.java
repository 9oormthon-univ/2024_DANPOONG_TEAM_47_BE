package univ.goormthon.kongju.domain.reservation.controller;

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
import univ.goormthon.kongju.domain.reservation.dto.request.ReservationRequest;
import univ.goormthon.kongju.domain.reservation.dto.response.ReservationResponse;
import univ.goormthon.kongju.domain.reservation.service.ReservationService;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kongju/reservation")
@Tag(name = "예약", description = "예약 API")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "예약 목록 조회", description = "현재 로그인한 사용자의 예약 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "예약 목록 조회 성공", content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 세션이 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorCode.class)))
    })
    @GetMapping("/list")
    public ResponseEntity<List<ReservationResponse>> getReservations(@RequestParam String memberId){
        return ResponseEntity.ok(reservationService.getReservations(memberId));
    }

    @Operation(summary = "예약 조회", description = "현재 로그인한 사용자의 예약 하나를 조회합니다.")
    @Parameters(value = {
            @Parameter(name = "parkingId", description = "주차장 ID")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "예약 조회 성공", content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 세션이 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorCode.class)))
    })
    @GetMapping
    public ResponseEntity<ReservationResponse> getReservation(@RequestParam String memberId, @RequestParam Long parkingId) {
        return ResponseEntity.ok(reservationService.getReservation(memberId, parkingId));
    }

    @Operation(summary = "예약 진행", description = "현재 로그인한 사용자의 예약을 진행합니다.")
    @Parameters(value = {
            @Parameter(name = "request", description = "예약 요청 JSON 형식의 데이터")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "예약 진행", content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
            @ApiResponse(responseCode = "404", description = "회원 세션이 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorCode.class)))
    })
    @PostMapping
    public ResponseEntity<ReservationResponse> reserve(@RequestParam String memberId, @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(reservationService.reserve(memberId, request));
    }

}
