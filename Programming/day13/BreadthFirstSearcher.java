public class BreadthFirstSearcher<N,W> {

public BreadthFirstSearcher() {

}

public boolean pathExists(IGraph<N,W> g, INode<N> s, INode<N> e) {
  boolean destinationFound = false;
  IEdge<N,W>[] theNeighbors = g.getEdgesFrom(s);
  for (int i = 0; i < theNeighbors.length; i++) {
    if (theNeighbors[i].getDestination() == e) {
      destinationFound = true;
      return destinationFound;
    }
    else {
      destinationFound = pathExists(g,theNeighbors[i].getDestination(),e);
      if (destinationFound) { return true; }
    }
  }
  return destinationFound;
}

public IList<INode<N>> getPath(IGraph<N,W> g, INode<N> s, INode<N> e) {
  DoubleLinkList<INode<N>> toReturn = new DoubleLinkList<INode<N>>();
  RingQueue<INode<N>> myQueue = new RingQueue<INode<N>>(100);
  DoubleLinkList<INode<N>> keepTrack = new DoubleLinkList<INode<N>>();

  try {
  myQueue.enqueue(s);
} catch (OverFlowException err) {}

  while (myQueue.size > 0) {
    INode<N> curr = null;

    try {
    curr = myQueue.dequeue();
    keepTrack.append(curr);
  } catch (UnderFlowException err) {}

    if (curr.equals(e)) {
      return toReturn;   ////true that there is a path
    }

    else if (g.getEdgesFrom(curr) != null) {

      boolean beenThere = false;
      IEdge<N,W>[] theNeighbors = g.getEdgesFrom(curr);    //array to store the edges from curr
      for (int j = 0; j < keepTrack.size(); j++){
        INode<N> compare = keepTrack.fetch(j);
        for (int k = 0; k < theNeighbors.length; k++) {
          INode<N> compare2 = theNeighbors[k].getDestination();
          if (compare.equals(compare2)) {
            beenThere = true;
          }
        }
      }


      if (beenThere == false){
      for (int i = 0; i < theNeighbors.length; i++){
        try {
          myQueue.enqueue(theNeighbors[i].getDestination());
        } catch (OverFlowException err) {}
    }
    }
    }
    else {
      continue;
    }
  }
  return null;   //false that there is a path
}


public static void main(String[] args) {
  BreadthFirstSearcher test = new BreadthFirstSearcher();
  IGraph<String,Double> g = new Graph();

  //a test graph:
  INode<String> start = g.addNode("start");
  INode<String> node1 = g.addNode("node1");
  INode<String> node2 = g.addNode("node2");
  INode<String> node3 = g.addNode("node3");
  INode<String> node4 = g.addNode("node4");
  INode<String> end = g.addNode("end");
  g.addEdge(start,node1,1.0);
  g.addEdge(start,node2,1.0);
  g.addEdge(node1,end,1.0);
  g.addEdge(node1,node3,1.0);
  g.addEdge(node3,node2,1.0);
  g.addEdge(node3,node4,1.0);



  System.out.println(test.pathExists(g,start,end));
  System.out.println(test.getPath(g,start,end));

  // IList<INode<String>> pathGot = test.getPath(g,start,end);
  // for (int i = 0; i < pathGot.size(); i++){
  //   System.out.println(pathGot.fetch(i).getValue());
  // }

}

}
