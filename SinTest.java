package lab11;

import org.junit.Test;

/**
 * Created by viacheslav on 25.05.15.
 */
public class SinTest {
    @Test
    public void testSinCounting() throws Exception {
        Sin sin = new Sin(10, 100000); // transferring main parametrs to the method
        System.out.println(sin.SinCounting()); // start the nessesary Threads number and returning result
    }
}