import java.util.*;

public class Graph<T> {
  private Map<T, List<T>> adjList;

  /**
   * Contructor of Graph class.
   */
  public Graph() {
    this.adjList = new HashMap<>();
  }

  /**
   * Adds a vertex to the graph.
   * @param vertex: the vertex to be added. 
   */
  public void addVertex(T vertex) {
    if (this.adjList.get(vertex) != null) {
      System.out.println("WARNING: Already contained vertex");
      return;
    }

    List<T> neighbors = new ArrayList<>();
    this.adjList.put(vertex, neighbors);
  }

  /**
   * Adds a vertex to the graph.
   * @param vertex: the vertex to be added. 
   * @param neighbors: the neighbors of the vertex
   */
  public void addVertex(T vertex, List<T> neighbors) {
    if (this.adjList.get(vertex) != null) {
      System.out.println("WARNING: Already contained vertex");
      return;
    }

    this.adjList.put(vertex, neighbors);
  }

  /**
   * Adds an edge to an existing vertex
   * @param vertex: the vertex to be added. 
   * @param neighbors: the neighbors of the vertex
   */
  public void addEdge(T v1, T v2) {
    if (this.adjList.get(v1) == null) {
      throw new IllegalArgumentException("V1 is not in Map!");
    }

    if (this.adjList.get(v2) == null) {
      throw new IllegalArgumentException("V2 is not in Map!");
    }

    this.adjList.get(v1).add(v2);
    this.adjList.get(v2).add(v1);
  }

  /**
   * Finds the size of the graph (the number of vertices)
   * @return int: the number of vertices in the graph
   */
  public int getVertexCount() {
    return this.adjList.size();
  }

  /**
   * Checks if the vertex is in the graph.  
   * @param vertex: the vertex to be checked
   * @return boolean: whether the vertex is in the graph
   */
  public boolean hasVertex(T vertex) {
    return (this.adjList.get(vertex) != null);
  }

  /**
   * Finds all the neighbors of a vertex.
   * @param vertex: the vertex that needs to have its neighbors found
   * @return List<T>: the list of the neighbors of the vertex
   */
  public List<T> neighbors(T vertex) {
    return this.adjList.get(vertex);
  }

  /**
   * Checks if two vertices of neighbors of each other
   * @param v1: the first vertex
   * @param v2: the second vertex
   * @return boolean: whether the two vertices provided are neighbots
   */
  public boolean isNeighbor(T v1, T v2) {
    return this.adjList.get(v1).contains(v2);
  }

  /**
   * Returns the adjacency list of the graph
   * @return Map<T, List<T>> the adjacency list of the graph
   */
  public Map<T, List<T>> getAdjList() {
    return this.adjList;
  }

  public Set<T> getVertices() {
    return this.adjList.keySet();
  }

}