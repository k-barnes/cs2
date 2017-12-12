// import java.util.swap;

public class SelectSort<T extends Comparable> implements ISort<T> {

  public SelectSort() {
  }

  public void sort(T[] arr){
    for (int i=0; i < arr.length-1; i++){
      int lowIndex = i;
      for (int j=arr.length-1; j>i; j--){
        if (arr[j].compareTo(arr[lowIndex]) < 0){
          lowIndex = j;
        }
      }
      arr = swap(arr, i, lowIndex);
    }
  }

  public T[] swap(T[] arr, int idx, int idxLow){
    T store = arr[idx];
    arr[idx] = arr[idxLow];
    arr[idxLow] = store;
    return arr;
  }

  public String sortName(){
    return "this is SelectSort";
  }

  //main method to test the class
  public static void main(String[] args){
    Integer[] arr = {4,3,8,6,1,5,9,0,7,4,2,1};
    SelectSort test = new SelectSort();
    test.sort(arr);
    System.out.println(test.sortName());

    for (int i=0; i<arr.length; i++){
      System.out.println(arr[i]);
    }

  }

}
