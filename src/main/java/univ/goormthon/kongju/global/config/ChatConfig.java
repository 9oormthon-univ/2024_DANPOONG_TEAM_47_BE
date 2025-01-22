package univ.goormthon.kongju.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.upgrade.TomcatRequestUpgradeStrategy;
import univ.goormthon.kongju.domain.chat.handler.CustomWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ChatConfig implements WebFluxConfigurer {

    @Bean
    public HandlerMapping webSocketHandlerMapping() {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/ws/chat", new CustomWebSocketHandler());
        int order = -1; // 우선순위

        return new SimpleUrlHandlerMapping(map, order);
    }

    @Bean
    public WebSocketService webSocketService() {
        TomcatRequestUpgradeStrategy strategy = new TomcatRequestUpgradeStrategy();
        strategy.setMaxSessionIdleTimeout(0L);
        strategy.setMaxBinaryMessageBufferSize(10 * 1024 * 1024); // Binary 10 MB
        strategy.setMaxTextMessageBufferSize(10 * 1024 * 1024); // Text 10 MB
        return new HandshakeWebSocketService();
    }
}
