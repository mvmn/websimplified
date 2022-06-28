package x.mvmn.tmp.websimplified.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import x.mvmn.tmp.websimplified.ws.WebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @Autowired
  private WebSocketHandler wsHandler;

  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(wsHandler, "/ws");
  }
}
