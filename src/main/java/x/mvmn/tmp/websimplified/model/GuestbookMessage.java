package x.mvmn.tmp.websimplified.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestbookMessage {
  @NotBlank
  @NotNull
  protected String author;
  @NotBlank
  @NotNull
  protected String message;
  protected Long timestamp;
}
