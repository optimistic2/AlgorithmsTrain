package warmUp;


import java.util.Arrays;

/**
 * @author biyanchen
 * @date 2021/5/27 8:25 下午
 */
public class SortAlgorithmsTrain {

    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 11, 22, 1, 66, 33, 4};
        System.out.println(Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        System.out.println("+++" + left + "+++" + right + "++++++");
        sort(arr, left, mid);
        System.out.println("____" + left + "___" + right + "__________");
        sort(arr, mid + 1, right);
        System.out.println("=========" + left + "====" + right + "=======");
        merge2(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int center, int right) {
        int[] tempArr = new int[arr.length];
        int l1 = left, third = left, mid = center + 1;
        while (left <= center && mid <= right) {
            if (arr[left] < arr[mid]) {
                tempArr[third++] = arr[left++];
            } else {
                tempArr[third++] = arr[mid++];
            }
        }
        while (mid <= right) {
            tempArr[third++] = arr[mid++];
        }
        while (left <= center) {
            tempArr[third++] = arr[left++];
        }
        while (l1 <= right) {
            arr[l1] = tempArr[l1++];
        }
    }

    private static void merge2(int[] arr, int left, int center, int right) {
        int l1 = left, third = 0, mid = center + 1, length = right - left + 1;
        int[] tempArr = new int[length];
        while (left <= center && mid <= right) {
            if (arr[left] < arr[mid]) {
                tempArr[third++] = arr[left++];
            } else {
                tempArr[third++] = arr[mid++];
            }
        }
        while (mid <= right) {
            tempArr[third++] = arr[mid++];
        }
        while (left <= center) {
            tempArr[third++] = arr[left++];
        }
        for (int i = 0; i < length; i++) {
            arr[l1 + i] = tempArr[i];
        }
    }

}
