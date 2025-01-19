package univ.goormthon.kongju.global.oauth2.provider;

import java.util.Map;

public interface OAuth2UserInfoProvider {

    Map<String, Object> getUserInfo(String accessToken);
    String getProviderName(); // kakao, google, naver, ...
}
