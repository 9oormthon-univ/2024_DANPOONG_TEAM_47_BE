package univ.goormthon.kongju.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import univ.goormthon.kongju.domain.chat.handler.CustomWebSocketHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                .setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // destination header가 /pub으로 시작하는 메시지는 @MessageMapping으로 라우팅
        registry.setApplicationDestinationPrefixes("/pub");

        // destination header가 /sub으로 시작하는 메시지는 in-memory message broker로 라우팅
        registry.enableSimpleBroker("/sub");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new CustomWebSocketHandler();
    }
}
