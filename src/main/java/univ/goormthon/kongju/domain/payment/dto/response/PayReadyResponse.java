package univ.goormthon.kongju.domain.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PayReadyResponse(
        @JsonProperty("tid") String tid,
        @JsonProperty("next_redirect_pc_url") String nextRedirectPcUrl
) {
}
