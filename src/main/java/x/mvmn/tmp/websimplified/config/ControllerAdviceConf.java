package x.mvmn.tmp.websimplified.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import x.mvmn.tmp.websimplified.exception.NotFound;

@RestControllerAdvice
public class ControllerAdviceConf {

  @ExceptionHandler(NotFound.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public String handleNotFound(NotFound nf) {
    return "Not found";
  }
}
