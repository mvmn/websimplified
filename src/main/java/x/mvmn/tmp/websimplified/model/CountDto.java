package x.mvmn.tmp.websimplified.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountDto {
  private final int count;
}
