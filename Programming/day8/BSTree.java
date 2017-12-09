public class BSTree<K extends Comparable <K>,V> implements IDict<K,V>  {  //extends comparable <K>,V> implements IDict<K,V>
  Node<K,V> root;
  Node<K,V> curr;
  int size;
  K[] keysArray;


  public BSTree(){
    root = null;
    curr = null;
    size = 0;
  }


  public V add(K k, V v) {   //takes in param key and value and places new node with this value in the tree
    curr = root;                   //look at the root first
    if (size == 0){
      root = new Node<K,V> (k, v);
      size++;
      return null;
    }
    return helper(k, v);
  }


  public V helper(K k, V v){   //helper method for insert is recursive
    //does the desired key already have a value in the tree?
    if ((k.compareTo(curr.getKey())==0)) {
      V oldValue = curr.getValue();
      curr.setValue(v);
      return oldValue;
    }
    //does the new node fit as a direct child?
    if ((k.compareTo(curr.getKey())<0 && curr.getLeft()==null) || (k.compareTo(curr.getKey())>0 && curr.getRight()==null) || (curr.isLeaf())) {
      if (k.compareTo(curr.getKey())<0){
        curr.setLeft(new Node<K,V>(k, v));
        size++;
      }
      else {
        curr.setRight(new Node<K,V>(k, v));
        size++;
      }
      return null;
    }
    //travel along tree until you reach a place where  one of the first two if statements is caught and the node is added
    if (k.compareTo(curr.getKey())<0){
      curr = curr.getLeft();
    }
    else{
      curr = curr.getRight();
    }
    return helper(k, v);
  }


  public V remove(K k) {
    //find the node to remove and it's parent
    Node<K,V> parent = null;
    curr = root;
    while(curr.getKey() !=k) {
      if (k.compareTo(curr.getKey())>0){
      parent = curr;
      curr = curr.getRight();
      }
      else if (k.compareTo(curr.getKey())<0){
      parent = curr;
      curr = curr.getLeft();
      }
      else if (curr.isLeaf() && curr.getKey().compareTo(k) != 0) {
        return null;
      }
      else {
        return null;
      }
  }
    //case1: its a leaf, just remove
    if(curr.isLeaf()==true){
      if(parent.getRight()==curr){
        V itsValue = curr.getValue();
        parent.setRight(null);
        size--;
        return itsValue;
      }
      else {
        V itsValue = curr.getValue();
        parent.setLeft(null);
        size--;
        return itsValue;
      }
    }
    //case2: it has one child, replace node with children
    if(curr.getRight()==null || curr.getLeft()==null){
      if(curr.getRight()==null){
        if (parent.getRight()==curr){
          V itsValue = curr.getValue();
          parent.setRight(curr.getLeft());
          size--;
          return itsValue;
        }
        else {
          V itsValue = curr.getValue();
          parent.setLeft(curr.getLeft());
          size--;
          return itsValue;
        }
        }
      else {
        if (parent.getRight()==curr){
          V itsValue = curr.getValue();
          parent.setRight(curr.getRight());
          size--;
          return itsValue;
        }
        else {
          V itsValue = curr.getValue();
          parent.setLeft(curr.getRight());
          size--;
          return itsValue;
        }
      }
    }
    //case3: node to remove is the root
    if (k == root.getKey()){
      Node<K,V> swapParent = curr;
      Node<K,V> rootsRightChild = curr.getRight();
      Node<K,V> rootsLeftChild = curr.getLeft();
      Node<K,V> swapNode = curr.getRight();   //variable to follow our swapping down the tree
      while(swapNode.getLeft()!=null){
        swapParent = swapNode;
        swapNode = swapNode.getLeft();
      }
      //have left most node, check if it has right child?
      if (swapNode.getRight() != null) {
        swapParent.setLeft(swapNode.getRight());
        swapNode.setRight(rootsRightChild);
        swapNode.setLeft(rootsLeftChild);
        root = swapNode;
        size--;
      }
      //if it doesn't have child set swap parent to have null left children
      else {
        swapParent.setLeft(null);
        swapNode.setRight(rootsRightChild);
        swapNode.setLeft(rootsLeftChild);
        root = swapNode;
        size--;
      }
    }
    //case4: internal node deletion. go right once, go left untial you can't, remove and store the leftmost node and replace is with the node you wanted to remove with it, then remove the node u wanted to
    Node<K,V> swapParent = curr;
    Node<K,V> swapNode = curr.getRight();   //variable to follow our swapping down the tree
    while(swapNode.getLeft()!=null){
      swapParent = swapNode;
      swapNode = swapNode.getLeft();
    }
    //have left most node
    V itsValue = swapNode.getValue();
    swapParent.setLeft(swapNode.getRight());
    swapNode.setLeft(curr.getLeft());
    swapNode.setRight(curr.getRight());
    if (parent.getRight()==curr) {parent.setRight(swapNode);}
    else if (parent.getLeft()==curr) {parent.setLeft(swapNode);}
    size--;
    return itsValue;
  }


  public int size() {
    return size;
  }


  public V fetch(K k){
    curr = root;
    if (size==0) {
      return null;
    }
    while (curr.getKey().compareTo(k) != 0){
      if (k.compareTo(curr.getKey())>0) {
        if (curr.isLeaf() == true){
          break;
        }
        //go right
        curr = curr.getRight();
      }
      else {
        if (curr.isLeaf() == true) {
          break;
        }
        //go left
        curr = curr.getLeft();
      }
    }
      //breaking out of the while loop means we have found key or run out of children
      if (curr.getKey().compareTo(k) == 0) {
        return curr.getValue();
      }
      else {
        return null;
      }
  }


  public K[] keys() {
    keysArray = (K[]) new Comparable[size];        //initialize array, cast it
    helpKeys(keysArray, 0, root);                 //calls the recursive function to traverse the list
    return keysArray;
  }


  public void helpKeys(K[] k, int next, Node<K,V> travNode){
    if (travNode.isLeaf()) {
      k[next] = travNode.getKey();
      next++;
    }
    helpKeys(k, next, travNode.getLeft());
    k[next] = travNode.getKey();
    next++;
    helpKeys(k, next, travNode.getRight());
    // k[next] = travNode.getKey();
    // next++;
    return;
  }


  public static void main(String[] argv){
    BSTree test = new BSTree();
    test.add("abc", 1);
    test.add("bcd", 2);
    // test.remove("bcd");
    test.add("cde", 3);
    System.out.println(test.add("cde", 99));
    test.add("def", 4);
    test.add("efg", 5);
    test.add("fgh", 6);
    test.add("ghi", 7);
    test.add("hij", 8);
    test.add("ijk", 9);
    test.add("jkl", 10);
    test.size();
    System.out.println(test.fetch("abc"));
    System.out.println(test.fetch("bcd"));
    System.out.println(test.fetch("cde"));
    System.out.println(test.fetch("def"));
    System.out.println(test.fetch("efg"));
    System.out.println(test.fetch("fgh"));
    System.out.println(test.fetch("ghi"));
    System.out.println(test.fetch("hij"));
    System.out.println(test.fetch("ijk"));
    System.out.println(test.fetch("jkl"));
    System.out.println(test.size());
    for (int i=0; i<test.size(); i++){
      System.out.println(test.keys()[i]);
    }
  }

}



 class Node<K,V> {
    K key;
    V value;
    Node<K,V> left;
    Node<K,V> right;

  public Node(K k, V v){   //constructor makes a new node
    key = k;
    value = v;
  }

  public void setLeft(Node<K,V> l){   //metod sets left child for given node
    left = l;
  }

  public void setRight(Node<K,V> r){  //metod sets right child for given node
    right = r;
  }

  public Node<K,V> getLeft(){
    return left;
  }

  public Node<K,V> getRight(){
    return right;
  }

  public boolean isLeaf(){
    if (left==null && right==null) {   //means this node has no children so its a leaf
      return true;
    }
    return false;
  }

  public K getKey(){
    return key;
  }

  public void setKey(K k){
    key = k;
  }

  public void setValue(V v){
    value = v;
  }

  public V getValue(){
    return value;
  }



  }
