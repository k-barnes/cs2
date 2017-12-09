public class Dict<K extends Comparable <K>,V> implements IDict<K,V> {
  DoubleLinkList<Cell<K,V>> list;
  int size;
  K[] keysArray;

  public Dict() {
    list = new DoubleLinkList();
    size = 0;
  }

  public V add(K k, V v){
    //case1: the list is empty, append first cell!
    if (size==0){
      Cell<K,V> toAdd = new Cell(k, v);
      list.append(toAdd);
      size++;
      return null;
    }
    //search for key in list
    list.jumpToHead();
    boolean found = false;                     //variable to see whether key has been found in list
    for (int i =0; i < size; i++) {
      if (list.fetch().getKey() == k){
        found = true;
        break;
      }
      list.next();
    }
    if (found == true) {  //case2: there is a key already in the list that matches the one we're looking for
      V oldValue = list.fetch().getValue();
      list.fetch().setValue(v);
      return oldValue;
    }
    else if (found == false) { //case3: the key we're looking for is not already in the list
      Cell<K,V> toAdd = new Cell(k, v);
      list.append(toAdd);
      size++;
      return null;
    }
    return null;
  }

  public V remove(K k){
    if (size==0){
      return null;
    }
    //search for key in list
    list.jumpToHead();
    boolean found = false;                     //variable to see whether key has been found in list
    for (int i =0; i < size; i++) {
      if (list.fetch().getKey() == k){
        found = true;
        break;
      }
      list.next();
    }
    if (found == true && list.fetch().getValue() != null) {  //case2: there is a key already in the list that matches the one we're looking for
      V oldValue = list.fetch().getValue();
      list.remove();
      size--;
      return oldValue;
    }
    if (found == true && list.fetch().getValue() != null) {
      list.remove();
      size--;
      return null;
    }
    if (found == false) {
      return null;
    }
    return null;
  }

  public int size() {
    return size;
  }

  public V fetch(K k){
    if (size==0){
      return null;
    }
    list.jumpToHead();
    while (list.fetch().getKey() != k){       //while loop repeats as long as the current key isn't equal to the desired key
      list.next();
      if (list.fetch() == list.tail){
        break;
      }
    }
    if (list.fetch().getKey() == k){
      return list.fetch().getValue();
    }
    else {
      return null;
    }
  }

  public K[] keys() {
    keysArray = (K[]) new Comparable[size];
    list.jumpToHead();
    for (int i=0; i < size; i++) {
      keysArray[i] = list.fetch().getKey();
      list.next();
    }
    return keysArray;
  }

  class Cell<K,V> {
    K key;
    V value;

    public Cell(K k, V v){
      key = k;
      value = v;
    }

    public K getKey(){
      return key;
    }

    public V getValue(){
      return value;
    }

    public void setKey(K k){
      key = k;
    }

    public void setValue(V v){
      value = v;
    }
  }

public static void main(String[] argv){
  Dict test = new Dict();
  test.add("stra", "fjskdjf");
  test.add("strooo", "fjskdjf");
  test.add("waho", "fjskdjf");
  test.add("lala", "fjskdjf");
  test.remove("strooo");
  System.out.println(test.size());
  for (int i=0; i<test.size(); i++){
    System.out.println(test.keys()[i]);
  }
  System.out.println(test.fetch("stra"));
  System.out.println(test.fetch("waho"));
  System.out.println(test.fetch("lala"));
}

}
