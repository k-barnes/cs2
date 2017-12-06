
public class ArrayList<T> implements IList<T> {
T[] backing;
int curr;  //note this will be equal to backing.length, so it is the empty space in front of the last value of list
int size;


public ArrayList() {
  backing = (T[])new Object[0];   //casting
  curr = 0;
  size = 0;
}

public void append(T v) {      //function adds given value to the end of the list
  T[] t = (T[])new Object[curr+1];
  if (curr==0){
    t[curr] = v;
    backing = t;
    curr++;
  }
  else {
  for (int i=0; i<curr; i++){
    t[i] = backing[i];
  }
  t[curr] = v;
  backing = t;
  curr++;
  }
}

public void remove(){    //removes item from most recent filled place on the list, curr-1
  T[] t = (T[])new Object[curr+1];
  for (int i=0; i<curr-1; i++){
    t[i] = backing[i];
  }
  t[curr-1] = null;
  backing = t;
  curr--;
}

public void remove(int idx){  //removes item from specified index and shifts the rest of the list back one index
  T[] t = (T[])new Object[curr+1];
  if (idx<1){
    for(int i=0; i<curr-1; i++){
      t[i] = backing[i+1];
    }
    backing = t;
    curr--;
  }
  else {
    for (int i=0; i<idx; i++){
      t[i] = backing[i];
    }
    for (int i=idx; i<curr-1; i++){
      t[i] = backing[i+1];
    }
    backing = t;
    curr--;
  }
}

public void move(int sidx, int didx){   //moves item in specified source index to specified destination index
  T[] t = (T[])new Object[curr+1];
  for(int i=0; i<sidx; i++){
    t[i]=backing[i];
  }
  for (int i=sidx; i<didx; i++){
    t[i]=backing[i+1];
  }
  for (int i=didx; i<didx+1; i++){
    t[i] = backing[sidx];
  }
  for (int i=didx+1; i<curr; i++){
    t[i] = backing[i];
  }
  backing = t;
}

public T fetch(){
  return backing[curr-1];
}

public T fetch(int idx){  //gets and returns the value at the specified list index
  int i = idx;
  return backing[i];
}

public void next(){
  curr++;
}

public void prev(){
  curr--;
}

public void jumpToTail(){
  curr = backing.length;  //to the lst element on the list
}

public void jumpToHead(){
  curr = 1;
}

public int size(){
  size = backing.length;
  return size;
}

public void printMe(){
  for (int i=0; i<curr; i++) {
    System.out.print( " " + fetch(i) + " ");
  }
}

// public static void main (String[] argv){
//   ArrayList first = new ArrayList();
//   first.append(1);
//   first.append(2);
//   first.append(3);
//   first.append(4);
//   first.append(5);
//   first.append(6);
//   first.printMe();
//   first.jumpToTail();
//   System.out.println(first.fetch());
// }

}
