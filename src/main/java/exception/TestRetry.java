package exception;

/**
 * Created by biyanchen on 2020/2/20.
 */
public class TestRetry {
    public static void main(String[] args) {
        try {
            System.out.println("结果：" + new SimpleRetryTemplate<String>() {

                @Override
                public String invoke() throws Exception {
                    int i = 1 / 0;
                    return "J";
                }
            }.retryWithException(Exception.class, 3).executeWithRetry());
        } catch (Exception e) {
            System.out.println("异常");
            e.printStackTrace();
        }
    }
}
