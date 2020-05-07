package multi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by biyanchen on 2020/5/5.
 */
public class Multi_AtomicInteger {
    static AtomicInteger threadNO = new AtomicInteger(1);
    static Thread t2 = null;
    static Thread t1 = null;

    public static void main(String[] args) {
        final char[] chars1 = "ABCDEFG".toCharArray();
        final char[] chars2 = "1234567".toCharArray();


        t1 = new Thread("t1") {
            @Override
            public void run() {
                for (char c : chars1) {
                    while (threadNO.get() != 1) {
                    }
                    System.out.print(c);
                    threadNO.set(2);
                }
            }
        };

        t2 = new Thread("t2") {
            @Override
            public void run() {
                for (char c : chars2) {
                    while (threadNO.get() != 2) {
                    }
                    System.out.print(c);
                    threadNO.set(1);
                }
            }
        };

        t1.start();
        t2.start();
    }
}
