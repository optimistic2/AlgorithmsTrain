package multi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by biyanchen on 2020/5/5.
 */
public class Multi_BlockingQueue {
    static Thread t2 = null;
    static Thread t1 = null;

    static BlockingQueue<String> queue1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> queue2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        final char[] chars1 = "ABCDEFG".toCharArray();
        final char[] chars2 = "1234567".toCharArray();


        t1 = new Thread() {
            @Override
            public void run() {
                for (char c : chars1) {
                    System.out.print(c);
                    try {
                        queue2.put("ok");
                        queue1.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t2 = new Thread() {
            @Override
            public void run() {
                for (char c : chars2) {
                    try {
                        queue2.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(c);
                    try {
                        queue1.put("ok");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}
