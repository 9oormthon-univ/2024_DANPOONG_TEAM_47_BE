package univ.goormthon.kongju.global.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import java.util.Map;

@SpringBootTest
class ChatConfigTest {

    @Autowired
    private Map<String, SimpleUrlHandlerMapping> handlerMappings;

    @Test
    public void testHandlerMappings() {
        System.out.println("Inspecting HandlerMappings...");

        handlerMappings.forEach((beanName, mapping) -> {
            System.out.println("HandlerMapping Bean: " + beanName);
            mapping.getHandlerMap().forEach((path, handler) -> {
                System.out.println("Mapped Path: " + path + " -> Handler: " + handler);
            });
        });
    }

}