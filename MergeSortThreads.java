package lab11;

/**
 * Created by viacheslav on 21.05.15.
 * Реализовать параллельную версию алгоритма сортировки слиянием (mergesort)
 * не использовать блоки синхронизации, использовать join и volatile
 */
    public class MergeSortThreads implements Runnable {
        private final int[] array; // shared between threads
        private final int startPos; // start position for sorting
        private final int endPos; // end position for sorting

        public MergeSortThreads(int [] array, int startPos, int endPos) { // constructor for instance creating
            this.array = array;
            this.startPos = startPos;
            this.endPos = endPos;
        }

        public void sorting(int startPos, int endPos) { // main method - will call recursively in the new threads
            if (endPos - startPos <= 1) {
                return;
            }
            int middlePos = (startPos + endPos) / 2;
            Thread th1 = new Thread(new MergeSortThreads(array, startPos, middlePos));
            Thread th2 = new Thread(new MergeSortThreads(array, middlePos, endPos));
            th1.start(); //sorting(startPos, middlePos) - starting another thread
            th2.start(); //sorting(middlePos, endPos) - starting another thread

            try {
                th1.join();
                th2.join();
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread() + " interrupted unexpectedly");
                return;
            }
            merge(startPos, middlePos, endPos);
        }

        public void merge(int startPos, int middlePos, int endPos) { // method for merging purposes
            int leftPos = startPos, rightPos = middlePos;

            while (leftPos != rightPos && rightPos < endPos) {
                if (array[leftPos] > array[rightPos]) {
                    int temp = array[rightPos];
                    System.arraycopy(array, leftPos, array, leftPos + 1, rightPos - leftPos);
                    array[leftPos] = temp;
                    ++rightPos;
                }
                ++leftPos;
            }
        }
        @Override
        public void run() {
            sorting(startPos, endPos);
        }  // calling sorting method recursively

        public void print() {  // method for printing array
            System.out.print("{");
            for (int elem : array) System.out.print( elem + " ");
            System.out.println("}");
        }
    }
