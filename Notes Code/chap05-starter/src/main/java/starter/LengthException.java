package starter;

public class LengthException extends RuntimeException{
  public LengthException() {
  }

  /**
   * Constructs a new IndexException with the specified detail message.
   *
   * @param message the detail message. The detail message is saved for
   *                later retrieval by the getMessage() method.
   */
  public LengthException(String message) {
    super(message);
  }
}
