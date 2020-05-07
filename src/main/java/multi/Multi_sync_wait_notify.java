package multi;

/**
 * Created by biyanchen on 2020/5/5.
 */
public class Multi_sync_wait_notify {

    static Thread t2 = null;
    static Thread t1 = null;
    static volatile boolean t2Started = false;

    public static void main(String[] args) {
        final char[] chars1 = "ABCDEFG".toCharArray();
        final char[] chars2 = "1234567".toCharArray();

        final Object o = new Object();

        t1 = new Thread() {
            @Override
            public void run() {
                synchronized (o) {
                    while (!t2Started) {
                        try {
                            o.wait();  // 有wait可以不要 t2Started 标识和循环
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (char c : chars1) {
                        System.out.print(c);
                        o.notify();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notify();
                }
            }
        };

        t2 = new Thread() {
            @Override
            public void run() {
                synchronized (o) {
                    t2Started = true;
                    for (char c : chars2) {
                        System.out.print(c);
                        o.notify();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notify();
                }
            }
        };

        t1.start();
        t2.start();
    }
}
