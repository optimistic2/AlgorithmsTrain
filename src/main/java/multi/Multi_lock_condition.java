package multi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by biyanchen on 2020/5/5.
 */
public class Multi_lock_condition {
    static Thread t2 = null;
    static Thread t1 = null;

    public static void main(String[] args) {
        final char[] chars1 = "ABCDEFG".toCharArray();
        final char[] chars2 = "123456".toCharArray();
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        t1 = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (char c : chars1) {
                        System.out.print(c);
                        condition.signal();
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    condition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        t2 = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (char c : chars2) {
                        System.out.print(c);
                        condition.signal();
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    condition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        t1.start();
        t2.start();
    }
}
