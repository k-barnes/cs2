public class MergeSort<T extends Comparable> implements ISort<T> {

  public MergeSort() {
  }

  public T[] split(T[] arr) {
    if (arr.length == 1){ return arr; }
    else if (arr.length%2 == 0){  //the array is big enough to break into parts and is EVEN
      int middle = arr.length/2;
      T[] a = (T[])new Comparable[middle];
      T[] b = (T[])new Comparable[middle];
      for (int i=0; i<middle; i++){
        a[i] = arr[i];
        b[i] = arr[middle+i];
      }
      T[] a_sorted = split(a);
      T[] b_sorted = split(b);
      return merge(a_sorted, b_sorted);
    }
    else {
      int middle = (arr.length-1)/2;
      T[] a = (T[])new Comparable[middle];
      T[] b = (T[])new Comparable[middle+1];
      for (int i=0; i<middle; i++){
        a[i] = arr[i];
        b[i] = arr[middle+i];
      }
      b[middle] = arr[arr.length-1];
      T[] a_sorted = split(a);
      T[] b_sorted = split(b);
      return merge(a_sorted, b_sorted);
    }
  }

  public T[] merge(T[] a, T[] b){
    T[] output = (T[])new Comparable[a.length+b.length];
    int a_index = 0;
    int b_index = 0;
    for (int i=0; i<output.length; i++){
      if (a_index == a.length) {
        output[i] = b[b_index];
        b_index++;
      }
      else if (b_index == b.length || a[a_index].compareTo(b[b_index])<0){
        output[i] = a[a_index];
        a_index++;
      }
      else {   //so b[b_index].compareTo(a[a_index])<0
        output[i] = b[b_index];
        b_index++;
      }
    }
    return output;
  }

  public void sort(T[] arr){
    arr = split(arr);
    for (int i=0; i<arr.length; i++){
      System.out.println(arr[i]);
    }
  }

  public String sortName(){
    return "this is MergeSort";
  }

  //a main method to test the class!
  public static void main(String[] args){
    Integer[] arr = new Integer[10];
    arr[0] = 4;
    arr[1] = 3;
    arr[2] = 7;
    arr[3] = 1;
    arr[4] = 9;
    arr[5] = 5;
    arr[6] = 6;
    arr[7] = 6;
    arr[8] = -8;
    arr[9] = 0;
    MergeSort test = new MergeSort();
    test.sort(arr);
    System.out.println(test.sortName());
  }
}
