package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * An undirected graph of <code>Node</code>s.
 * @author anya
 * @param <T> the type of values in this <code>Graph</code>'s <code>Nodes</code>
 */
public class Graph<T> {

  // instance variables

  /**
   * Creates a new empty <code>Graph</code>.
   */
  // constructor

  /**
   * Returns a <code>Set</code> of <code>Node</code>s in this <code>Graph</code>.
   * @return a <code>Set</code> of <code>Node</code>s in this <code>Graph</code>
   */
  // getNodes()

  /**
   * Returns the <code>Node</code> from this <code>Graph</code> with the given ID.
   * 
   * @param id the ID of the <code>Node</code> to return
   * @return the <code>Node</code> from this <code>Graph</code> with the given ID, if such
   *         <code>Node</code> exists in this <code>Graph</code>
   * @throws NoSuchNodeException if there is no <code>Node</code> with ID <code>id</code> in this
   *         <code>Graph</code>
   */
  // getNode

  /**
   * Returns a <code>Set</code> of neighbours of the given <code>Node</code>.
   * @param node the <code>Node</code> whose neighbours are returned
   * @return a <code>Set</code> of neighbours of <code>node</code>
   */
  public Set<Node<T>> getNeighbours(Node<T> node) {
    return nodeToNeighbours.get(node);
  }

  /**
   * Returns whether <code>Node</code>s with the given IDs are adjacent in this <code>Graph</code>.
   * 
   * @param id1 ID of the node to test for adjacency
   * @param id2 ID of the node to test for adjacency
   * @return true, if <code>Node</code>s with IDs id1 and id2 are adjacent in this
   *         <code>Graph</code> (there is an edge between them), and false otherwise
   * @throws NoSuchNodeException if node with id1 or id2 is not in this <code>Graph</code>
   */
   // areAdjacent

  /**
   * Returns whether the given <code>Node</code>s are adjacent in this <code>Graph</code>.
   * 
   * @param node1 the node to test for adjacency with node2
   * @param node2 the node to test for adjacency with node1
   * @return true, if node1 and node2 are adjacent in this <code>Graph</code> (there is an edge
   *         between them), and false otherwise
   * @throws NoSuchNodeException if node1 or node2 are not in this <code>Graph</code>
   */
   // areAdjacent

  /**
   * Returns the number of nodes in this <code>Graph</code>.
   * @return the number of nodes in this <code>Graph</code>
   */
  public int getNumNodes() {
    return getNodes().size();
  }

  /**
   * Returns the number of edges in this <code>Graph</code>.
   * @return the number of edges in this <code>Graph</code>
   */
  public int getNumEdges() {
    int total = 0;
    for (Node<T> node : getNodes()) {
      total += getNeighbours(node).size();
    }
    return total / 2;  // the graph is undirected
  }

  /**
   * Adds a new <code>Node</code> with the given ID and value to this <code>Graph</code>, if there
   * is not a <code>Node</code> with the given ID in this <code>Graph</code>.
   * @param id the ID of the new <code>Node</code>
   * @param value the value of the new <code>Node</code>
   */
  // addNode(int id, T value)

  /**
   * Adds an edge between the given nodes in this <code>Graph</code>. If there is already an edge
   * between node1 and node2, does nothing. A self-edge is not added.
   * @param node1 the node to add an edge to and from node2
   * @param node2 the node to add an edge to and from node1
   * @throws NoSuchNodeException if node1 or node2 is not in this <code>Graph</code>
   */
  // addEdge

  /**
   * Adds an edge between the nodes with the given IDs in this <code>Graph</code>.
   * @param id1 ID of the node to add an edge to and from
   * @param id2 ID of the node to add an edge to and from
   * @throws NoSuchNodeException if there is no <code>Node</code> with ID id1 or ID id2 in this
   *         Graph
   */
  // addEdge

  @Override
  public String toString() {
    String result = String.format("Number of nodes: %d\nNumber of edges: %d\n",
        getNumNodes(), getNumEdges());

    for (Node<T> node: getNodes()) {
      result += String.format("%s is adjacent to: ", node);
      for  (Node<T> neighbour: getNeighbours(node)) {
        result += String.format("%s ", neighbour);
      }
      result += "\n";
    }
    return result;
  }
}
