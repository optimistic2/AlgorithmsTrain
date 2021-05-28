package warmUp;

import java.util.Arrays;

/**
 * @author biyanchen
 * @date 2021/3/26 2:04 下午
 */
public class MyTest {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 5, 87, 8, 9, 10, 0};
        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(buildHeap(arr)));
        System.out.println(Arrays.toString(sort(arr)));
    }

    public static int[] buildHeap(int[] arr) {
        int childIndex = arr.length - 1;
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {

            if (arr[i] > arr[childIndex]) {
                int t = arr[i];
                arr[i] = arr[childIndex];
                arr[childIndex] = t;
            }
            childIndex = i * 2 + 1;
            if (childIndex < arr.length - 2 && arr[childIndex] > arr[childIndex + 1]) {
                childIndex++;
            }
        }
        return arr;
    }

    public static int[] down(int[] arr, int start, int length) {
        for (int i = (length - 2) / 2; i >= 0; i--) {
            int childIndex = i * 2 + 1;
            int parentIndex = i + start;
            int trueChildIndex = childIndex + start;
            // 并且做孩子大于右孩子
            if (childIndex < length - 2 && arr[trueChildIndex] > arr[trueChildIndex + 1]) {
                trueChildIndex++;
            }
            // 如果父节点大于子节点交换parent和最小孩子节点位置
            if (arr[parentIndex] > arr[trueChildIndex]) {
                int t = arr[parentIndex];
                arr[parentIndex] = arr[trueChildIndex];
                arr[trueChildIndex] = t;
            }
        }
        return arr;
    }

    public static int[] sort(int[] arr) {
        for (int i = arr.length; i > 1; i--) {
            down(arr, arr.length - i, i);
        }
        return arr;
    }

}
