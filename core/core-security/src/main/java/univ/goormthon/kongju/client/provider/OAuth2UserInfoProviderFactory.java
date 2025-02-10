package univ.goormthon.kongju.client.provider;

import org.springframework.stereotype.Component;
import univ.goormthon.kongju.exception.UnsupportedProviderException;
import univ.goormthon.kongju.exception.dto.ErrorCode;

import java.util.List;

@Component
public class OAuth2UserInfoProviderFactory {

    private final List<OAuth2UserInfoProvider> providers;

    public OAuth2UserInfoProviderFactory(List<OAuth2UserInfoProvider> providers) {
        this.providers = providers;
    }

    public OAuth2UserInfoProvider getProvider(String providerName) {
        return providers.stream()
                .filter(provider -> provider.getProviderName().equalsIgnoreCase(providerName))
                .findFirst()
                .orElseThrow(() -> new UnsupportedProviderException(ErrorCode.UNSUPPORTED_PROVIDER));
    }
}
