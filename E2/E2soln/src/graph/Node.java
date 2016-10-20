package graph;

/**
 * A node in a Graph.
 * @author anya
 */
public class Node<T> implements Comparable<Node<T>> {

  private int id;  // the ID of this Node
  private T value; // the value stored in this Node

  /**
   * Creates a new <code>Node</code> with the given ID and value.
   * @param id the ID of the new <code>Node</code>
   * @param value the value of the new <code>Node</code>
   */
  public Node(int id, T value) {
    this.id = id;
    this.value = value;
  }

  /**
   * Returns the ID of this <code>Node</code>.
   * @return the ID of this <code>Node</code>
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the value of this <code>Node</code>.
   * @return the value of this <code>Node</code>
   */
  public T getValue() {
    return value;
  }

  /**
   * Sets the value of this <code>Node</code> to value.
   * @param value the new value for this <code>Node</code>
   */
  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format("(%d, %s)", id, value);
  }

  /**
   * {@inheritDoc}
   * Comparison is based on <code>Node</code> IDs.
   */
  @Override
  public int compareTo(Node<T> other) {
    return id - other.id;
  }
}