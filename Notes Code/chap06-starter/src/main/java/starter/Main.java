package starter;

public class Main {
  public static void main(String[] args){
    int[] numbers = new int[]{1,2,4,5,2};

//    for(int elements: numbers){
//      System.out.println(elements);
//    }
//    for (Integer val: values){
//      System.out.printf(val);
//    }
    /*
    Iterator<Integer> it = values.iterator();
    while(it.hasNext()){
      Integer val = it.next();
      Sout(val);
    }
     */
for(Integer i:numbers){
  System.out.println(i);
}
  }

}
