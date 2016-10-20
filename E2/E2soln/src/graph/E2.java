package graph;

public class E2 {

  /**
   * Creates and prints the Graph in the handout. Demonstrates behaviour
   * of some of the required methods.
   * @param args ignored
   */
  public static void main(String[] args) {
    Graph<String> graph = new Graph<>();

    System.out.println(graph); // initially empty

    for (int i = 1; i <= 6; i++) {
      graph.addNode(i, String.format("value%d", i));
    }

    try { // these should be added
      graph.addEdge(1, 2);
      graph.addEdge(1, 5);
      graph.addEdge(2, 5);
      graph.addEdge(2, 3);
      graph.addEdge(4, 3);
      graph.addEdge(4, 5);
      graph.addEdge(4, 6);
    } catch (NoSuchNodeException ex) {
      ex.printStackTrace();
    }

    System.out.println(graph);

    // this one should not be added
    try {
      graph.addEdge(5, 1);
    } catch (NoSuchNodeException ex) {
      ex.printStackTrace();
    }

    System.out.println(graph);

    // this should throw an exception
    try {
      graph.addEdge(42, 2);
    } catch (NoSuchNodeException ex) {
      System.out.println(String.format("No such node: %s.", ex.getMessage()));
    }

    // try to add a Node already in the graph
    graph.addNode(3, "newValue3");
    System.out.println(graph);
    System.out.println((new Node<String>(3, "v3")).equals(new Node<String>(3,"v3")));
 
    try { // these are safe
      System.out.println(String.format("1 and 2? %s.", graph.areAdjacent(1, 2)));
      System.out.println(String.format("2 and 6? %s.", graph.areAdjacent(2, 6)));
      System.out.println(String.format("3 and 4? %s.", graph.areAdjacent(3, 4)));
    } catch (NoSuchNodeException ex) {
      ex.printStackTrace();
    }    
  }
}
