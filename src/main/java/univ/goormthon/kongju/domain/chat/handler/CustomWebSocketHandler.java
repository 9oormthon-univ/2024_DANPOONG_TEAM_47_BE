package univ.goormthon.kongju.domain.chat.handler;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class CustomWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .doOnNext(webSocketMessage -> {
                    System.out.println(webSocketMessage.getPayloadAsText());
                })
                .then();
    }

}
