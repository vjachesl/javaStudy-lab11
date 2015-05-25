package lab11;

import org.junit.Test;

/**
 * Created by viacheslav on 25.05.15.
 */
public class MergeSortThreadsTest {

    @Test
    public void testSorting() throws Exception {
        int [] arr = {0,45,24,5,12,909,432,45,2334,88,5,7,12,43,76,34,1,4,9,10};
        MergeSortThreads merg = new MergeSortThreads(arr, 0, 19);
        merg.print();
        merg.sorting(0, arr.length);
        merg.print();
    }
}