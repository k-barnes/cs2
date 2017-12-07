public class DoubleLinkList<T> implements IList<T> {
  IDLink<T> head;
  IDLink<T> tail;
  IDLink<T> curr;


public DoubleLinkList(){
  head = null;
  curr = null;
  tail = null;
}

public void insert(int idx, T v){
  if (idx==0){
    IDLink<T> q = new Cell<T>(v, null, null);
    head.setPrev(q);
    q.setNext(head);
    q.setPrev(null);
    head = q;
  }
  else if (idx>0){
  curr = head;
  for (int i=0; i<idx; i++){
    curr = curr.getNext();
  }
  IDLink<T> q = new Cell<T>(v, null, null);
  IDLink<T> p = curr.getPrev();
  q.setPrev(p);
  q.setNext(curr);
  curr.setPrev(q);
  p.setNext(q);
}
tail = tail.getNext();
}

public void append(T v){
  if (curr==null) {
    IDLink<T> first = new Cell<T>(v, null, null);
    head = first;
    curr = first;
    tail = first;
  }
  else {
    IDLink<T> q = new Cell(v, null, null);
    tail.setNext(q);
    q.setPrev(tail);
    tail = q;
    curr = q;
  }
}

public void remove(){
  if (curr==null){
  }
  else if (curr==tail) {
    curr = curr.getPrev();
    tail.setPrev(null);
    curr.setNext(null);
    tail = curr;
  }
  else if (curr==head) {
    IDLink p = curr;
    curr = curr.getNext();
    p.setNext(null);
    curr.setPrev(null);
    head = curr;
  }
  else {
    IDLink p = curr.getNext();
    curr = curr.getPrev();
    p.setPrev(curr);
    curr.setNext(p);
  }
}

public void remove(int idx){
  if (idx == 0){
    head = head.getNext();
  }
  else {
  IDLink<T> q = head;
  curr = q;
  for (int i=0; i<idx-1; i++){
    q = q.getNext();
    curr = q;
  }
  IDLink<T> p = curr.getNext();
  curr.setNext(p.getNext());
  curr.setPrev(curr.getPrev());
  }
}

public void move(int sidx, int didx){
  if(sidx==0){
    IDLink<T> save = head;
    IDLink<T> afterSave = save.getNext();
    afterSave.setPrev(null);
    head = afterSave;
    curr = head;
    for (int i=sidx+1; i<didx-1; i++){
      curr = curr.getNext();
    }
    IDLink<T> dindx = curr.getNext();
    save.setPrev(dindx);
    save.setNext(dindx.getNext());
    dindx.setNext(save);
    dindx.setPrev(curr);
  }
  else if (sidx<didx){
  IDLink<T> q = head;
  curr = q;
  for (int i=0; i<sidx-1; i++){
    q = q.getNext();
    curr = q;
  }
  IDLink<T> save = curr.getNext();
  IDLink<T> afterSave = save.getNext();
  curr.setNext(afterSave);
  curr.setPrev(curr.getPrev());
  afterSave.setPrev(curr);
  for (int i=sidx; i<didx-1; i++){
    curr = curr.getNext();
  }
  IDLink<T> dindx = curr.getNext();
  save.setNext(dindx.getNext());
  save.setPrev(dindx);
  dindx.setNext(save);
  dindx.setPrev(curr);
  }
  else if (didx==0){
    T save = this.fetch(sidx);
    this.remove(sidx);
    this.insert(didx, save);
  }
  else if (didx<sidx) {
    IDLink<T> p = head;
    curr = p;
    for (int i=0; i<sidx-1; i++){
      p = p.getNext();
      curr = p;
    }
    IDLink<T> save = curr.getNext();
    IDLink<T> afterSave = save.getNext();
    curr.setNext(afterSave);
    afterSave.setPrev(curr);

    IDLink<T> r = head;
    curr = r;
    for (int i=0; i<didx-1; i++){
        r = r.getNext();
        curr = r;
    }
    save.setNext(curr.getNext());
    save.setPrev(curr);
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
  IDLink<T> a = head;
  for (int i=0; i<idx; i++){
    if (a.getNext()==null){
      return null;
    }
    IDLink<T> b = a.getNext();
    a = b;
  }
  T v = a.getValue();
  return v;
}

public void next(){
  if (curr.getNext()==null){
    return;
  }
  curr = curr.getNext();
}

public void prev(){
  if (curr.getPrev()==null){
    return;
  }
  curr = curr.getPrev();
}

public void jumpToTail() {
  curr = tail;
}

public void jumpToHead(){
  curr = head;
}

public int size(){
  if (head==null){
    return 0;
  }
  else if (head==tail) {
    return 1;
  }
  IDLink<T> q = head;
  int i = 0;
  while (q.getNext() != tail){
    q = q.getNext();
    i++;
  }
  return i+2;
}

public static void main(String[] argv){
  DoubleLinkList first = new DoubleLinkList();
  first.append(1);
  first.append(2);
  first.append(3);
  first.append(4);
  first.append(5);
  first.append(6);
  first.append(7);
  first.append(8);
  first.append(9);
  System.out.println(first.fetch(0));
  System.out.println(first.fetch(1));
  System.out.println(first.fetch(2));
  System.out.println(first.fetch(3));
  System.out.println(first.fetch(4));
  System.out.println(first.fetch(5));
  System.out.println(first.fetch(6));
  System.out.println(first.fetch(7));
  System.out.println(first.fetch(8));
  System.out.println(first.fetch(9));
  System.out.println(first.fetch(10));
}

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
