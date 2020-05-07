package multi;

/**
 * Created by biyanchen on 2020/5/5.
 */
public class Multi_While {

    static Thread t2 = null;
    static Thread t1 = null;

    enum RunState {
        RUN1, RUN2
    }

    static volatile RunState runState = RunState.RUN1;

    public static void main(String[] args) {
        final char[] chars1 = "ABCDEFG".toCharArray();
        final char[] chars2 = "1234567".toCharArray();


        t1 = new Thread() {
            @Override
            public void run() {
                for (char c : chars1) {
                    while (runState != RunState.RUN1) {
                    }
                    System.out.print(c);
                    runState = RunState.RUN2;
                }
            }
        };

        t2 = new Thread() {
            @Override
            public void run() {
                for (char c : chars2) {
                    while (runState != RunState.RUN2) {
                    }
                    System.out.print(c);
                    runState = RunState.RUN1;
                }
            }
        };

        t1.start();
        t2.start();
    }
}
