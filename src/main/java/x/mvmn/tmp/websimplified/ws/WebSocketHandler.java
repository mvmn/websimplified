package x.mvmn.tmp.websimplified.ws;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

  protected final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message)
      throws InterruptedException, IOException {

    for (WebSocketSession webSocketSession : sessions.values()) {
      String value = message.getPayload();
      System.out.println("Received message '" + value + "' from " + webSocketSession.getId() + " / "
          + webSocketSession.getRemoteAddress());
      // webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
    }
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    sessions.put(session.getId(), session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session.getId());
  }

  public void sentToAll(String message) {
    sessions.values().forEach(session -> {
      try {
        session.sendMessage(new TextMessage(message));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
