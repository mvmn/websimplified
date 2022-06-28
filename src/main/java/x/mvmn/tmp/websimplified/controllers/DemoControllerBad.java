package x.mvmn.tmp.websimplified.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import x.mvmn.tmp.websimplified.model.CountDto;
import x.mvmn.tmp.websimplified.service.GuestbookService;

@RestController
@RequestMapping("/guestbook")
public class DemoControllerBad {

  @Autowired
  private GuestbookService guestbookService;


  @PostMapping
  public Object post(@RequestBody JsonNode payload) {
    String action = payload.get("action").asText();

    switch (action) {
      case "getcount":
        return CountDto.builder().count(guestbookService.getCount()).build();
      case "get":
        return guestbookService.getMessage(payload.get("index").intValue());
      case "getlist":
        return guestbookService.getMessages(payload.get("pageSize").intValue(),
            payload.get("pageNumber").intValue());
      case "delete":
        return guestbookService.deleteMessage(payload.get("index").intValue());
      case "create":
        guestbookService.addMessage(payload.get("author").asText(),
            payload.get("message").asText());
    }

    return null;
  }
}
