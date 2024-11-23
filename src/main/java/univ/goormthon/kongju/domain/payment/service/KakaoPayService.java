package univ.goormthon.kongju.domain.payment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import univ.goormthon.kongju.domain.payment.dto.request.PayApproveRequest;
import univ.goormthon.kongju.domain.payment.dto.request.PayRequest;
import univ.goormthon.kongju.domain.payment.dto.response.PayApproveResponse;
import univ.goormthon.kongju.domain.payment.dto.response.PayReadyResponse;
import univ.goormthon.kongju.domain.reservation.entity.Reservation;

@Service
public class KakaoPayService {

    @Value("${kakaopay.secret-key}")
    private String secretKey;

    @Value("${kakaopay.cid}")
    private String cid;

    public PayReadyResponse ready(Reservation reservation, int totalFee) {
        PayRequest request = PayRequest.builder()
                .partnerOrderId(String.valueOf(reservation.getId()))
                .partnerUserId(String.valueOf(reservation.getMemberId()))
                .itemName("공유 주차장 결제 진행")
                .quantity(1)
                .totalAmount(totalFee)
                .taxFreeAmount(0)
                .approvalUrl("http://localhost:8080/api/kongju/payment/approval")
                .cancelUrl("http://localhost:8080/api/kongju/payment/cancel")
                .failUrl("http://localhost:8080/api/kongju/payment/fail")
                .build();

        HttpEntity<PayRequest> entity = new HttpEntity<>(request, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        ResponseEntity<PayReadyResponse> response = restTemplate.postForEntity(url, request, PayReadyResponse.class);

        return response.getBody();
    }

    public PayApproveResponse approve(String pgToken, String tid) {
        PayApproveRequest request = PayApproveRequest.builder()
                .cid(cid)
                .tid(tid)
                .partnerOrderId("1")
                .partnerUserId("1")
                .pgToken(pgToken)
                .build();

        HttpEntity<PayApproveRequest> entity = new HttpEntity<>(request, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";

        return restTemplate.postForObject(url, request, PayApproveResponse.class);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY " + secretKey);
        headers.set("Content-type", "application/json");

        return headers;
    }
}
