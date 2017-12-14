public class Edge<N,W> implements IEdge<N,W> {
  Node<N> source;
  Node<N> destination;
  W weight;

  public Edge(Node<N> s, Node<N> d, W w) {
    source = s;
    destination = d;
    weight = w;
  }

  public Node<N> getSource() {
    return source;
  }

  public Node<N> getDestination() {
    return destination;
  }

  public W getWeight() {
    return weight;
  }

  public boolean equals(Object o) {
    Edge<N,W> O = (Edge<N,W>)o;
    boolean equal = false;
    if (this.source == O.source && this.destination == O.destination) {
      equal = true;
    }
    return equal;
  }

}
