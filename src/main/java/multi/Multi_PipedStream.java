package multi;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by biyanchen on 2020/5/5.
 */
public class Multi_PipedStream {
    static Thread t2 = null;
    static Thread t1 = null;


    public static void main(String[] args) {
        final char[] chars1 = "ABCDEFG".toCharArray();
        final char[] chars2 = "1234567".toCharArray();

        final PipedInputStream input1 = new PipedInputStream();
        final PipedInputStream input2 = new PipedInputStream();
        final PipedOutputStream output1 = new PipedOutputStream();
        final PipedOutputStream output2 = new PipedOutputStream();

        try {
            input1.connect(output1);
            input2.connect(output2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String msg = "Your turn";

        t1 = new Thread() {
            @Override
            public void run() {
                byte[] buffer = new byte[9];
                for (char c : chars1) {
                    try {
                        input1.read(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.print(c);
                    try {
                        output2.write(msg.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t2 = new Thread() {
            @Override
            public void run() {
                byte[] buffer = new byte[9];
                for (char c : chars2) {
                    try {
                        output1.write(msg.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        input2.read(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.print(c);

                }
            }
        };

        t1.start();
        t2.start();
    }
}
