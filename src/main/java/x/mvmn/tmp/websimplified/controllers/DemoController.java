package x.mvmn.tmp.websimplified.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import x.mvmn.tmp.websimplified.exception.NotFound;
import x.mvmn.tmp.websimplified.model.CountDto;
import x.mvmn.tmp.websimplified.model.GuestbookMessage;
import x.mvmn.tmp.websimplified.service.GuestbookService;

@RestController
@RequestMapping("/guestbook/messages")
public class DemoController {

  @Autowired
  private GuestbookService guestbookService;

  @GetMapping("count")
  public CountDto getCount() {
    return CountDto.builder().count(guestbookService.getCount()).build();
  }

  @GetMapping("{index}")
  public GuestbookMessage get(@PathVariable(name = "index") int index) {
    return guestbookService.getMessage(index).orElseThrow(() -> new NotFound());
  }

  @GetMapping
  public List<GuestbookMessage> get(
      @RequestParam(defaultValue = "10", required = false, name = "pageSize") int pageSize,
      @RequestParam(defaultValue = "0", required = false, name = "pageNumber") int pageNumber) {
    return guestbookService.getMessages(pageSize, pageNumber);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void post(@RequestBody @Valid GuestbookMessage msg) {
    guestbookService.addMessage(msg.getAuthor(), msg.getMessage());
  }

  @DeleteMapping("{index}")
  public void delete(@PathVariable(name = "index") int index) {
    boolean deleted = guestbookService.deleteMessage(index);
    if (!deleted) {
      throw new NotFound();
    }
  }
}
