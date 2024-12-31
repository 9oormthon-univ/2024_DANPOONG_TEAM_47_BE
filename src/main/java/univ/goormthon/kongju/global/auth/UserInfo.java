package univ.goormthon.kongju.global.auth;

import java.util.Map;

public record UserInfo(
        String id,
        Map<String, Object> attributes
) {
}
