package univ.goormthon.kongju.global.oauth2;

import java.util.Map;

public record UserInfo(
        String id,
        Map<String, Object> attributes
) {
}
