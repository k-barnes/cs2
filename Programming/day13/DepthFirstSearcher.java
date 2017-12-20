public class DepthFirstSearcher<N,W> implements ISearcher<N,W> {


public DepthFirstSearcher() {
}

public boolean pathExists(IGraph<N,W> g, INode<N> s, INode<N> e) {
  boolean destinationFound = false;
  IEdge<N,W>[] theNeighbors = g.getEdgesFrom(s);
  if (theNeighbors != null) {
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
}
  return destinationFound;
}

public IList<INode<N>> getPath(IGraph<N,W> g, INode<N> s, INode<N> e) {
  LinkStack<INode<N>> toSearch = new LinkStack<INode<N>>(100);
  LinkStack<INode<N>> thePath = new LinkStack<INode<N>>(100);
  DoubleLinkList<INode<N>> visited = new DoubleLinkList<INode<N>>();
  int numAdded = 0;
  if (pathExists(g,s,e) == false) {
    return null;
  }
  if (s == e) {
    return thePath.toDoubleLinkList();     //note: i build this method in linkstack class, look there
  }
  else {

    try {
    toSearch.push(s);
  } catch (OverFlowException err) {}

    while (!toSearch.isEmpty()) {
      INode<N> curr = null;

      try {
      curr = toSearch.pop();
      } catch (UnderFlowException err){}

      if (curr == e) {
        return thePath.toDoubleLinkList();
      }

      else if (g.getEdgesFrom(curr) != null) {
        IEdge<N,W>[] theNeighbors = g.getEdgesFrom(curr);    //array to store the edges from curr

        for (int i=0; i < theNeighbors.length; i++){

          numAdded = 0;
          INode<N> miniCurr = theNeighbors[i].getDestination();    //check if destination node has already been visited
          boolean alreadyVisited = false;
          for (int j = 0; j< visited.size(); j++){
            if (miniCurr.equals(visited.fetch(j))){
              alreadyVisited = true;
            }
          }

          if (!alreadyVisited){
          try {
          toSearch.push(miniCurr);
          numAdded++;                      //increment this counter which tells how many of currs neighbors got added to toSearch
        } catch (OverFlowException err){}
        }

        visited.append(miniCurr);

        }

        if (numAdded != 0){
        try {
        thePath.push(curr);
      } catch (OverFlowException err) {}
      }
      }

      else {  //no more neighbors
        continue;
      }
    }
  }
  return thePath.toDoubleLinkList();    //note: created this method in the linkstack class, look there
}

public static void main(String[] args) {
  DepthFirstSearcher test = new DepthFirstSearcher();
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
  IList<INode<String>> pathGot = test.getPath(g,start,end);
  for (int i = 0; i < pathGot.size(); i++){
    System.out.println(pathGot.fetch(i).getValue());
  }

}

}
