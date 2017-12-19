//Note: this code is largely from coding together in class
public class LinkStack<T> implements IStack<T> {
  Cell<T> top;
  int size;  //is the current size of the stack
  int maxSize;

public LinkStack(int length){  //constructor makes a new LinkStack instance. in which size is initialized
  size = 0;
  top = null;
  maxSize = length;
}


public void push(T v) throws OverFlowException{
  Cell<T> n = new Cell<T>(v, top);
  top = n;
  size++;
}

public T pop() throws UnderFlowException{
  if(size==0) {throw new UnderFlowException();}
  T v = top.getValue();
  top = top.getPointer();
  size --;
  return v;
}

public boolean isEmpty(){    //return true if the stack is empty and false if not
  if (size == 0){
    return true;
  }
  return false;
}

public DoubleLinkList toDoubleLinkList() {
  DoubleLinkList<T> l = new DoubleLinkList<T>();
  DoubleLinkList<T> temp = new DoubleLinkList<T>();
  int staticSize = size;
  for (int i = 0; i < staticSize; i++){     //writes list in reverse order
    try {
    T testing = pop();
    temp.append( testing );
  } catch (UnderFlowException err) {}
  }
  for (int i = staticSize-1; i > -1; i--){     //remake list in right order
    l.append(temp.fetch(i));
  }
  return l;
}

// public static void main(String[] argv) throws Exception {    //a test case/file within this class
//   Stack<Integer> ls = new Stack<Integer>();
//   ls.push(1);
//   ls.push(2);
//   while (!ls.isEmpty()){
//     System.out.println(ls.pop());
//   }
// }

}

class Cell<T> {
  T value;
  Cell<T> pointer;

  public Cell(T v, Cell<T> p){   //constructor to make a new cell with know value to store in cell and "next cell in the stack" pointer
    value = v;
    pointer = p;
  }

  public void setValue(T v){
    value = v;
  }

  public T getValue() {
    return value;
  }

  public void setPointer(Cell p) {
    pointer = p;
  }

  public Cell<T> getPointer(){
    return pointer;
  }

}
