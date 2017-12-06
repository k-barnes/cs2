public class SingleLinkList<T> implements IList<T> {
  ISLink<T> head;
  ISLink<T> tail;
  ISLink<T> curr;

public SingleLinkList(){
  head = null;
  tail = null;
  curr = null;
}

public void insert(int idx, T v){
  curr = head;
  for (int i=0; i<idx; i++){
    curr = curr.getNext();
  }
  ISLink<T> q = new Cell1(v, null);
  q.setNext(curr.getNext());
  curr.setNext(q);
}

public void append(T v){
  if (curr==null){
    ISLink<T> first = new Cell1(v, null);
    head = first;
    curr = first;
    tail = first;
  }
  else {
    ISLink<T> q = new Cell1(v, null);
    tail.setNext(q);
    curr = q;
    tail = q;
  }
}

public void remove(){
  if (curr.getValue()==null){
  }
  else {
    this.prev();
    curr.setNext(null);
    tail = curr;
  }
}

public void remove(int idx){
  ISLink<T> q = head;
  for (int i=0; i<idx; i++){
    q = q.getNext();
    curr = q;
  }
  ISLink<T> p = curr.getNext();
  ISLink<T> r = p.getNext();
  curr.setNext(r);
}

public void move(int sidx, int didx){
  if (sidx==0){
    ISLink<T> save = head;
    curr = head;
    for (int i =sidx+1; i<didx-1; i++){
      curr =curr.getNext();
    }
    ISLink<T> afterdidx = curr.getNext();
    curr.setNext(save);
    save.setNext(afterdidx);
  }
  if (sidx<didx){
  ISLink<T> q = head;
  curr = q;
  for (int i=0; i<sidx-1; i++){
    q = q.getNext();
    curr = q;
  }
  ISLink<T> save = curr.getNext();
  ISLink<T> afterSave = save.getNext();
  curr.setNext(afterSave);
  for (int i=sidx; i<didx-1; i++){
    curr = curr.getNext();
  }
  ISLink<T> afterDestination = curr.getNext();
  save.setNext(afterDestination.getNext());
  afterDestination.setNext(save);
  }
  else {
    ISLink<T> q = head;
    curr = q;
    for (int i=0; i<sidx-1; i++){
      q = q.getNext();
      curr = q;
    }
    ISLink<T> save = curr.getNext();
    ISLink<T> afterSave = save.getNext();
    curr.setNext(afterSave);

    ISLink<T> p = head;
    curr = p;
    for (int i=0; i<didx-1; i++){
      p = p.getNext();
      curr = p;
    }
    ISLink<T> afterDestination = curr.getNext().getNext();
    save.setNext(afterDestination.getNext());
    curr.setNext(save);
  }
}

public T fetch(){
  T v = curr.getValue();
  return v;
}

public T fetch(int idx){
  if (head==null){
    return null;
  }
  ISLink<T> a = head;
  for (int i=0; i<idx; i++){
    if (a.getNext()==null){
      return null;
    }
    ISLink<T> b = a.getNext();
    a = b;
  }
  T v = a.getValue();
  return v;
}

public void next(){
  curr = curr.getNext();
}

public void prev(){
  if (curr == head) {
    return;
  }
  ISLink<T> q = head;
  while (q.getNext() != curr) {
    q = q.getNext();
  }
  curr = q;
}

public void jumpToTail() {
  curr = tail;
}

public void jumpToHead(){
  curr = head;
}

public int size(){
  ISLink<T> q = head;
  int i = 0;
  while (q.getNext() != tail){
    q = q.getNext();
    i++;
  }
  return i+2;
}

//a main method to do my own test of the methods i implement
public static void main(String[] argv) throws Exception {
  SingleLinkList first = new SingleLinkList();
  first.append(1);
  first.append(2);
  first.append(3);
  first.append(4);
  first.append(5);
  first.insert(2, 3);
  System.out.println(first.fetch(0));
  System.out.println(first.fetch(1));
  System.out.println(first.fetch(2));
  System.out.println(first.fetch(3));
  System.out.println(first.fetch(4));
  System.out.println(first.size());
}

}

class Cell1<T> implements ISLink<T> {
  T value;
  ISLink<T> pointer;

  public Cell1(T v, Cell1<T> p){
    value = v;
    pointer = p;
  }

  public T getValue(){
    return value;
  }

  public void setValue(T v){
    value = v;
  }

  public ISLink<T> getNext(){
    return pointer;
  }

  public void setNext(ISLink<T> c){
    pointer = c;
  }

}
