package univ.goormthon.kongju.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.goormthon.kongju.domain.payment.SessionUtils;
import univ.goormthon.kongju.domain.payment.dto.request.PayApproveRequest;
import univ.goormthon.kongju.domain.payment.dto.response.PayApproveResponse;
import univ.goormthon.kongju.domain.payment.dto.response.PayReadyResponse;
import univ.goormthon.kongju.domain.payment.service.KakaoPayService;
import univ.goormthon.kongju.domain.reservation.entity.Reservation;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kongju/payment")
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    @PostMapping("/ready")
    public ResponseEntity<PayReadyResponse> ready(@RequestBody Reservation reservation, @RequestParam int totalFee) {
        PayReadyResponse response = kakaoPayService.ready(reservation, totalFee);
        SessionUtils.addAttribute("tid", response.tid());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/approval")
    public ResponseEntity<PayApproveResponse> approval(@RequestParam("pg_token") String pgToken) {
        String tid = SessionUtils.getStringAttributeValue("tid");
        PayApproveResponse response = kakaoPayService.approve(pgToken, tid);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(@RequestParam String tid, @RequestParam int cancelAmount) {
        return ResponseEntity.ok(null);
    }
}
