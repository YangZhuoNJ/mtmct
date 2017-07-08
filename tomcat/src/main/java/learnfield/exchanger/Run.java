package learnfield.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/6/20.
 */
public class Run implements Runnable {

    public static volatile boolean isdone = true;

    private volatile Integer data;

    private Exchanger<Integer> exchanger = null;

    public Run(Exchanger<Integer> exchanger, Integer data) {
        this.data = data;
        this.exchanger = exchanger;
    }

    public void run() {

        while (isdone && !Thread.interrupted()) {

            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {

                int plus = exchanger.exchange(1);
                data += plus;
                System.out.println(Thread.currentThread().getName() + " " + data);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }
}
