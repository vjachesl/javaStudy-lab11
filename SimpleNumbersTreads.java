package lab11;

/**
 * Created by viacheslav on 21.05.15.
 * Написать программу для поиска простых чисел в n-потоках. Найденные простые числа просто
 * выводятся на экран.
 * не использовать блоки синхронизации, использовать join и volatile
 */

public class SimpleNumbersTreads { // running class for simple number finding
    public static void main(String[] args) { // running module for simple number finding
        Thread find1 = new Thread(new SimpleNumbersFinder(1,3,10000));
        Thread find2 = new Thread(new SimpleNumbersFinder(2,10000,100000));
        Thread find3 = new Thread(new SimpleNumbersFinder(3,100000,1000000));
        Thread find4 = new Thread(new SimpleNumbersFinder(4,1000000,10000000));
        find1.start();
        find2.start();
        find3.start();
        find4.start();
    }
}

 class SimpleNumbersFinder implements Runnable { //
     private int id;  // thread id #
     private int begin; // begin range
     private int end; // end range

     public SimpleNumbersFinder(int id, int begin , int end){ // constructor for receiving instance
         this.id = id;
         this.begin = begin;
         this.end = end;
     }
     public int isSimpleNumber(int number) { // checking for simple property - if result > 0 - number is not simple
         int counter =0;
         for (int i = 2; i<number; i++)  if (number%i==0) counter++;
         return counter;
     }

     @Override
     public void run() {
             for (int i= begin; i<end; i++) {
             if (isSimpleNumber(i)==0) // if the condition returns true - the number is simple and will print.
             System.out.println("Tread "+id+" found: "+i);
         }
     }
}
