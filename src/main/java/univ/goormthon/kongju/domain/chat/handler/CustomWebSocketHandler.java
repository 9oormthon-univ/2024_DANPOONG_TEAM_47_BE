package univ.goormthon.kongju.domain.chat.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Slf4j
public class CustomWebSocketHandler implements WebSocketHandler {

    private static final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if(sessionMap.isEmpty() || !(message instanceof TextMessage)) {
            session.close(CloseStatus.NOT_ACCEPTABLE);
            return;
        }


    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Transport Error: {}", exception.getMessage());

        if (session.isOpen()) {
            afterConnectionClosed(session, CloseStatus.SERVER_ERROR);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        session.close();
        sessionMap.remove(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
