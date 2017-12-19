import java.util.*;

public class Graph<N, W> implements IGraph<N, W> {
  INode<N>[] nodes;
  IEdge<N,W>[] edges;
  Node<N> curr;
  int nodesSize;
  int edgesSize;

  public Graph(){
    nodes = (INode<N>[])new Node[0];
    edges = (IEdge<N,W>[])new Edge[0];
    nodesSize = 0;
    edgesSize = 0;
    curr = null;
  }

  public INode<N>[] getNodeSet() {
    return nodes;
  }

  public INode<N>[] getNeighbors(INode<N> n) {
    IEdge<N, W>[] edgesFrom = getEdgesFrom(n);
    IEdge<N, W>[] edgesTo = getEdgesTo(n);
    INode<N>[] neighbors = (INode<N>[])new Node[edgesFrom.length + edgesTo.length];
    for (int i=0; i< edgesFrom.length; i++){
      IEdge<N, W> look = edgesFrom[i];
      neighbors[i] = look.getDestination();
    }
    for (int i=0; i< edgesTo.length; i++){
      Edge<N, W> look = (Edge<N, W>)edgesTo[i];
      neighbors[i] = look.getSource();
    }
    return neighbors;
  }

  public INode<N> addNode(N v) {
    Node<N> toAdd = new Node<N>(v);
    curr = toAdd;
    for (int i=0; i<nodesSize; i++) {
      if (toAdd.getValue().equals(nodes[i].getValue())){
        return (INode<N>)nodes[i];
      }
    }
    INode<N>[] temp = (INode<N>[])new Node[nodesSize+1];
    for(int i=0; i<nodesSize; i++){
      temp[i] = (Node<N>)nodes[i];
    }
    temp[nodesSize] = toAdd;
    nodes = temp;
    nodesSize++;
    return toAdd;
  }

  public IEdge<N,W>[] getEdgeSet() {
    return edges;
  }

  public IEdge<N, W>[] getEdgesFrom(INode<N> n) {
    int counter = 0;   //first count the edgesFrom
    for (int i=0; i<edgesSize; i++){
      Edge<N, W> look = (Edge<N, W>)edges[i];
      if (look.getSource() == n) {
        counter++;
      }
    }
    IEdge<N, W>[] edgesFrom = (IEdge<N, W>[])new Edge[counter];
    int idx = 0;
    for (int i=0; i<edgesSize; i++){
      Edge<N, W> look = (Edge<N, W>)edges[i];
      if (look.getSource() == n) {
        edgesFrom[idx] = look;
        idx++;
      }
    }
    if (idx == 0) {
      return null;
    }
    return edgesFrom;
  }

  public IEdge<N,W>[] getEdgesTo(INode<N> n) {
    int counter = 0;   //first count the edgesTo
    for (int i=0; i<edgesSize; i++){
      Edge<N, W> look = (Edge<N, W>)edges[i];
      if (look.getDestination() == n) {
        counter++;
      }
    }
    Edge<N, W>[] edgesTo = new Edge[counter];
    int idx = 0;
    for (int i=0; i<edgesSize; i++){
      Edge<N, W> look = (Edge<N, W>)edges[i];
      if (look.getDestination() == n) {
        edgesTo[idx] = look;
        idx++;
      }
    }
    if (idx == 0) {
      return null;
    }
    return edgesTo;
  }

  public void addEdge(INode<N> n1, INode<N> n2, W w) {
    Node<N> nOne = (Node<N>)n1;
    Node<N> nTwo = (Node<N>)n2;
    Edge<N, W> toAdd = new Edge<N, W>(nOne,nTwo,w);
    toAdd.source = nOne;
    toAdd.destination = nTwo;
    IEdge<N, W>[] temp = (IEdge<N, W>[])new Edge[edgesSize+1];
    for (int i=0; i<edgesSize; i++) {   //check if the edge to add already exists in "edges"
      if(toAdd.equals(edges[i])){
        break;
      }
    }
    for(int i=0; i<edgesSize; i++){
      temp[i] = edges[i];
    }
    temp[edgesSize] = toAdd;
    edges = temp;
    edgesSize++;
  }

  // public Node<N> nodeMaker(N v){
  //   Node<N> newNode = new Node<N>(v);
  //   for (int i=0; i<nodesSize; i++) {
  //     if (newNode.equals(nodes[i])){
  //       return (Node<N>)nodes[i];
  //     }
  //   }
  //   return newNode;
  // }

}
