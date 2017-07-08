package learnfield.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/6/20.
 */
public class ExchangerTest {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<Integer>();
        Runnable run1 = new Run(exchanger, 1);
        Runnable run2 = new Run(exchanger, 0);


        Thread t1 = new Thread(run1);
        Thread t2 = new Thread(run2);
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Run.isdone = false;

    }

    private static int data = 0;





}
