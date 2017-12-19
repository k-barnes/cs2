public class Node<N> implements INode<N> {
  N value;

  public Node(N v) {
    value = v;
  }

  public void setValue(N v) {
    value = v;
  }

  public N getValue() {
    return value;
  }
}
