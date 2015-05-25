package lab11;

import java.util.List;
import java.util.Vector;

/**
 * Created by viacheslav on 21.05.15.
 * Написать программу, суммирующую в n потоках ряд sin(k), где k пробегает целые числа от -N до N
 * не использовать блоки синхронизации, использовать join и volatile
 */

public class Sin implements Runnable{
    static int value; // container for needed value of k variable - one for all
    static int threads; // container for threads number - one for all;
    static List<Double> result; // array for results - one thread will update only it's own cell;
    int currentThreadNumber; // variable for current thread store purposes;

    public Sin(int threadsQuantiuty, int value){ // constructor for initialisation
        this.threads  = threadsQuantiuty;
        this.value = value;
        result= new Vector<>(threads);
    }
    public Sin(int threadNumber){  // constructor for threads - each new instance with the current thread number
        this.currentThreadNumber = threadNumber;
    }

    public Double SinCounting() throws InterruptedException {  // main running method - will start all threads
        Thread[] threadArr = new Thread[threads];              // and wait for it finishing.
        for (int i=0; i<threads; i++) {                        // after that - will summ all result
            threadArr[i] = new Thread(new Sin(i));
            threadArr[i].start();
        }
        for (Thread th : threadArr) th.join();
       return sinGetResult();
    }


    @Override
    public void run() {
        System.out.println("Tread" + currentThreadNumber+" was started");
        result.add(currentThreadNumber, 0d);
        for(int i=(value*-1)+currentThreadNumber; i<=value; i=i+threads)
            result.add(currentThreadNumber,result.get(currentThreadNumber)+Math.sin(i));
        System.out.println("Tread" + currentThreadNumber+" was finished");
    }

    private Double sinGetResult (){  // private method for summing all threads result into one value
        Double resultToReturn = 0d;
        for(Double res : result) resultToReturn=resultToReturn+res;
        return resultToReturn;
    }


}
