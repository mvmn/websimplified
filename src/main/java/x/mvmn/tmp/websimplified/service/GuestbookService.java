package x.mvmn.tmp.websimplified.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import x.mvmn.tmp.websimplified.model.GuestbookMessage;
import x.mvmn.tmp.websimplified.ws.WebSocketHandler;

@Service
public class GuestbookService {

  protected final List<GuestbookMessage> MESSAGES = Collections.synchronizedList(new ArrayList<>());

  @Autowired
  private WebSocketHandler wsHandler;

  @Autowired
  private ObjectMapper objectMapper;

  public void addMessage(String author, String message) {
    GuestbookMessage msg = GuestbookMessage.builder().author(author).message(message)
        .timestamp(System.currentTimeMillis() / 1000).build();
    MESSAGES.add(0, msg);

    try {
      wsHandler.sentToAll(objectMapper.writeValueAsString(msg));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  public int getCount() {
    return MESSAGES.size();
  }

  public List<GuestbookMessage> getMessages(int pageSize, int pageNumber) {
    int start = pageSize * pageNumber;
    int end = start + pageSize;
    if (start >= MESSAGES.size()) {
      return Collections.emptyList();
    }
    return MESSAGES.subList(start, Math.min(end, MESSAGES.size()));
  }

  public Optional<GuestbookMessage> getMessage(int index) {
    return index < MESSAGES.size() ? Optional.of(MESSAGES.get(index)) : Optional.empty();
  }

  public boolean deleteMessage(int index) {
    if (index < MESSAGES.size()) {
      MESSAGES.remove(index);
      return true;
    }
    return false;
  }
}
