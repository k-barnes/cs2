public class DoubleLinkList<T> {
  IDLink<T> head;
  IDLink<T> tail;
  IDLink<T> curr;


public DoubleLinkList(){
  head = new Cell (null, null, null);
  curr = new Cell (null, null, null);
  tail = new Cell (null, null, null);
}

public void append(T v){
  if (curr.getValue()==null) {
    IDLink<T> first = new Cell(v, null, null);
    head = first;
    curr = first;
    tail = first;
  }
  else {
    IDLink<T> q = new Cell(v, null, null);
    tail.setNext(q);
    tail = q;
    tail.setPrev(curr);
    curr = q;
  }
}

public void remove(){
  if (curr.getValue()==null){
  }
  else {
    curr = curr.getPrev();
    curr.setNext(null);
    curr.setPrev(curr.getPrev());
    tail = curr;
  }
}

public void remove(int idx){
  IDLink<T> q = head;
  for (int i=0; i<idx-1; i++){
    q = q.getNext();
    curr = q;
  }
  IDLink<T> p = curr.getNext();
  curr.setNext(p.getNext());
  curr.setPrev(curr.getPrev());
}

public void move(int sidx, int didx){
  IDLink<T> q = head;
  curr = q;
  for (int i=0; i<sidx-1; i++){
    q = q.getNext();
    curr = q;
  }
  IDLink<T> save = curr.getNext();
  curr.setNext(save.getNext());
  curr.setPrev(curr.getPrev());
  for (int i=sidx; i<didx-1; i++){
    curr = curr.getNext();
  }
  save.setNext(curr.getNext().getNext());
  IDLink<T> dindx = curr.getNext();
  dindx.setNext(save);
  save.setPrev(dindx);
}

public T fetch(){
  T v = curr.getValue();
  return v;
}

public T fetch(int idx){
  IDLink<T> a = head;
  for (int i=0; i<idx; i++){
    IDLink<T> b = a.getNext();
    a = b;
  }
  T v = a.getValue();
  return v;
}

public void next(){
  curr = curr.getNext();
}

public void prev(){
  curr = curr.getPrev();
}

public void jumpToTail() {
  curr = tail;
}

public void jumpToHead(){
  curr = head;
}

public int size(){
  IDLink<T> q = head;
  int i = 0;
  while (q.getNext() != tail){
    q = q.getNext();
    i++;
  }
  return i+2;
}

// public static void main(String[] argv){
//   DoubleLinkList first = new DoubleLinkList();
//   first.append(1);
//   first.append(2);
//   first.append(3);
//   first.append(4);
//   first.append(5);
//   first.append(6);
//   System.out.println(first.fetch(0));
//   System.out.println(first.fetch(1));
//   System.out.println(first.fetch(2));
//   System.out.println(first.fetch(3));
//   System.out.println(first.fetch(4));
//   System.out.println(first.fetch(5));
// }

}

class Cell<T> implements IDLink<T> {
  T value;
  IDLink<T> ptF;   //pointer looking forwards
  IDLink<T> ptB;   //pointer looking back

  public Cell(T v, Cell<T> f, Cell<T> b){
    value = v;
    ptF = f;
    ptB = b;
  }

  public T getValue(){
    return value;
  }

  public void setValue(T v){
    value = v;
  }

  public IDLink<T> getNext(){
    return ptF;
  }

  public void setNext(IDLink<T> c){
    ptF = c;
  }

  public IDLink<T> getPrev(){
    return ptB;
  }

  public void setPrev(IDLink<T> c){
    ptB = c;
  }

}
