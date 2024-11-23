package univ.goormthon.kongju.domain.payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PayApproveRequest(
        @JsonProperty("cid") String cid,
        @JsonProperty("tid") String tid,
        @JsonProperty("partner_order_id") String partnerOrderId,
        @JsonProperty("partner_user_id") String partnerUserId,
        @JsonProperty("pg_token") String pgToken
) {
}
