package univ.goormthon.kongju.global.oauth2.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuth2UserInfoProviderFactory {

    private final List<OAuth2UserInfoProvider> providers;

    public OAuth2UserInfoProvider getProvider(String providerName) {
        return providers.stream()
                .filter(provider -> provider.getProviderName().equalsIgnoreCase(providerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported provider: " + providerName));
    }
}

