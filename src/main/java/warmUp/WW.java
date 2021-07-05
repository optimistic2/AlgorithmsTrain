package warmUp;

/**
 * @author biyanchen
 * @date 2021/7/2 9:17 下午
 */
public class WW {

    public static void main(String[] args) {
        int[][] arr = {{1, 0, 1}, {1, 0, 1}, {0, 1, 1}};
//        System.out.println(arr.length);
        System.out.println(run(arr, 0, 0, 3, 3, false));
    }

    public static boolean run(int[][] arr, int i, int j, int m, int n, boolean flag) {
        if (i == (m - 1) && j == (n - 1) && arr[i][j] == 1) {
            return true;
        }
        // 回退
        if (arr[i][j] == 0) {
            if (flag) {
                if (i - 1 > 0) {
                    return run(arr, i - 1, j, m, n, true);
                } else {
                    return false;
                }
            } else {
                if (j - 1 > 0) {
                    return run(arr, i, j - 1, m, n, true);
                }
                return false;
            }
        }
        if (i + 1 < m && arr[i + 1][j] != 0) {
            return run(arr, i + 1, j, m, n, true);
        }
        if (j + 1 < n && arr[i][j + 1] != 0) {
            return run(arr, i, j+1, m, n, false);
        }
        return false;
    }
}
