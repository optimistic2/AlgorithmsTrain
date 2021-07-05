package warmUp;

/**
 * @author biyanchen
 * @date 2021/6/1 5:18 下午
 */
public class MinCount {

    public static void main(String[] args) {
        System.out.println(getMinCount2(63));
    }

    public static int getMinCount(int count) {
        int result = Integer.MAX_VALUE;
        if (count == 1) {
            return 1;
        }
        if (count == 5) {
            return 1;
        }
        if (count == 11) {
            return 1;
        }
        if (count - 1 >= 0) {
            result = Math.min(result, getMinCount(count - 1) + 1);
        }
        if (count - 5 >= 0) {
            result = Math.min(result, getMinCount(count - 5) + 1);
        }
        if (count - 11 >= 0) {
            result = Math.min(result, getMinCount(count - 11) + 1);
        }
        return result;
    }

    public static int getMinCount2(int count) {
        int result = 0;
        int[] fun = new int[105];
        for (int i = 1; i <= count; i++) {
            int p = Integer.MAX_VALUE;
            if (i - 1 >= 0) {
                p = Math.min(p, fun[i - 1] + 1);
            }
            if (i - 5 >= 0) {
                p = Math.min(p, fun[i - 5] + 1);
            }
            if (i - 11 >= 0) {
                p = Math.min(p, fun[i - 11] + 1);
            }
            fun[i] = p;
            result = p;
        }
        return result;
    }
}
