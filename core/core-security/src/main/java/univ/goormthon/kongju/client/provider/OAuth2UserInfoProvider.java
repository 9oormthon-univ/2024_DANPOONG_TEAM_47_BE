package univ.goormthon.kongju.client.provider;

import java.util.Map;

public interface OAuth2UserInfoProvider {

    Map<String, Object> getUserInfo(String accessToken);
    String getProviderName();
}