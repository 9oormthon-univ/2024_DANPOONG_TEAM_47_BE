package univ.goormthon.kongju.domain.payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PayRequest(
        @JsonProperty("cid") String cid,
        @JsonProperty("partner_order_id") String partnerOrderId,
        @JsonProperty("partner_user_id") String partnerUserId,
        @JsonProperty("item_name") String itemName,
        @JsonProperty("quantity") int quantity,
        @JsonProperty("total_amount") int totalAmount,
        @JsonProperty("tax_free_amount") int taxFreeAmount,
        @JsonProperty("approval_url") String approvalUrl,
        @JsonProperty("cancel_url") String cancelUrl,
        @JsonProperty("fail_url") String failUrl
) {
}
