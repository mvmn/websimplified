package x.mvmn.tmp.websimplified.exception;

public class NotFound extends RuntimeException {
  private static final long serialVersionUID = 4872073014224030313L;

  public NotFound() {}

  public NotFound(String message) {
    super(message);
  }
}
