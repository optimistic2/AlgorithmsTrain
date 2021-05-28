package warmUp;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author biyanchen
 * @date 2021/5/12 9:07 下午
 */
public class SumKey {

    static class Struct {

        int a;
        int b;

        public Struct(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Struct{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    public static List<Struct> getS(int[] arr, int k) {
        int start = 0, end = arr.length - 1, r = arr.length;
        if (arr[0] > k || arr[end] + arr[end - 1] < k) {
            throw new RuntimeException();
        }
        List<Struct> result = Lists.newArrayList();

        while (start < r) {
            int m = (start + r) / 2;
            if (arr[m] > k) {
                r = m - 1;
            } else if (arr[m] < k) {
                start = m + 1;
            } else {
                break;
            }
        }
        end = r;
        if (arr[end] + arr[end - 1] < k) {
            throw new RuntimeException();
        }
        start = 0;
        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum == k) {
                result.add(new Struct(arr[start], arr[end]));
                end--;
                start++;
            } else if (sum > k) {
                end--;
            } else {
                start++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 7, 12, 16, 22, 43, 51};
//        System.out.println(binSearch(arr, 9));
        System.out.println(getS(arr, 42));
    }

    public static int binSearch(int[] arr, int k) {
        int start = 0, end = arr.length;
        while (start < end) {
            int m = (start + end) / 2;
            if (arr[m] > k) {
                end = m - 1;
            } else if (arr[m] < k) {
                start = m + 1;
            } else {
                return m - 1;
            }
        }
        return end - 1;
    }

}
