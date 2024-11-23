package univ.goormthon.kongju.domain.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PayApproveResponse(
        @JsonProperty("aid") String aid,
        @JsonProperty("tid") String tid,
        @JsonProperty("cid") String cid,
        @JsonProperty("partner_order_id") String partnerOrderId,
        @JsonProperty("partner_user_id") String partnerUserId,
        @JsonProperty("payment_method_type") String paymentMethodType,

        @JsonProperty("amount") Amount amount,
        @JsonProperty("item_name") String itemName,
        @JsonProperty("approved_at") LocalDateTime approvedAt

        ) {
        public record Amount(
                @JsonProperty("total") int total,
                @JsonProperty("tax_free") int taxFree,
                @JsonProperty("vat") int vat,
                @JsonProperty("point") int point,
                @JsonProperty("discount") int discount) {}
}
