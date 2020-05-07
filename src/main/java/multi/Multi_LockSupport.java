package multi;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by biyanchen on 2020/5/5.
 */
public class Multi_LockSupport {
    static Thread t2 = null;
    static Thread t1 = null;

    public static void main(String[] args) {
        final char[] chars1 = "ABCDEFG".toCharArray();
        final char[] chars2 = "123456".toCharArray();


        t1 = new Thread() {
            @Override
            public void run() {
                for (char c : chars1) {
                    System.out.print(c);
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
                LockSupport.unpark(t2);
            }
        };

        t2 = new Thread() {
            @Override
            public void run() {
                for (char c : chars2) {
                    LockSupport.park();
                    System.out.print(c);
                    LockSupport.unpark(t1);
                }
                LockSupport.unpark(t1);
            }
        };

        t1.start();
        t2.start();
    }
}
