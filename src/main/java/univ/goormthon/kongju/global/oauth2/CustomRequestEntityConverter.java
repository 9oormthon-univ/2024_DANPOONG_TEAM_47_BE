package univ.goormthon.kongju.global.oauth2;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class CustomRequestEntityConverter implements Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> {
    private final OAuth2AuthorizationCodeGrantRequestEntityConverter defaultConverter = new OAuth2AuthorizationCodeGrantRequestEntityConverter();

    @Override
    public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest request) {
        // 기본 변환기를 사용해 초기 엔터티 생성
        RequestEntity<?> entity = defaultConverter.convert(request);

        // 기존 요청 본문에 client_id와 client_secret 추가
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>((MultiValueMap<String, String>) entity.getBody());
        body.add("client_id", request.getClientRegistration().getClientId());
        body.add("client_secret", request.getClientRegistration().getClientSecret());

        // Content-Type 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        return new RequestEntity<>(body, headers, HttpMethod.POST, entity.getUrl());
    }
}
