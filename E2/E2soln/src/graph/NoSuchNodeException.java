package graph;

/**
 * Exception that is thrown when a non-existing <code>Node</code> is referenced.
 * @author anya
 */
public class NoSuchNodeException extends Exception {

  private static final long serialVersionUID = 6087721090539288166L;

  /**
   * Creates a new <code>NoSuchNodeException</code>.
   */
  public NoSuchNodeException() {}

  /**
   * Creates a new <code>NoSuchNodeException</code> with the given message.
   * @param message the message for this <code>NoSuchNodeException</code>
   */
  public NoSuchNodeException(String message) {
    super(message);
  }
}
