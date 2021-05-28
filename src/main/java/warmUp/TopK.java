package warmUp;

import java.util.Arrays;

/**
 * @author biyanchen
 * @date 2021/5/12 3:40 下午
 */
public class TopK {

    public static void main(String[] args) {
        int[] arr = {666, 1, 3, 11, -1, 96, 4, 6, 5, 5, 87, 8, -2, 9, 10, 0, 17};
//        System.out.println("结果：" + Arrays.toString(getTopK(arr, 8)));
//        buildHeap(arr);
//        System.out.println("结果：" + Arrays.toString(arr));

        System.out.println("结果：" + partitionOfK(arr, 0, arr.length - 1, 5));
//        System.out.println("开始：" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("结果：" + Arrays.toString(arr));
    }

    public static int[] getTopK(int[] list, int k) {
        if (k >= list.length) {
            throw new RuntimeException();
        }
        int[] result = new int[k];
        int index = 0;
        for (Integer num : list) {
            if (index < k) {
                result[index] = num;
            } else if (index == k) {
                // 构建小顶堆
                System.out.println("前：" + Arrays.toString(result));
                smallUp(result);
                System.out.println("后：" + Arrays.toString(result));
                replaceHeapTop(k, result, num);
            } else {
                replaceHeapTop(k, result, num);
            }
            index++;
        }
        return result;
    }

    // 替换最小值并下沉
    private static void replaceHeapTop(int k, int[] result, Integer num) {
        if (num > result[0]) {
            int parent = 0;
            result[0] = num;
            for (int i = 0; i < k; i++) {
                int child = 2 * i + 1;
                if (child + 1 < k && result[child] > result[child + 1]) {
                    child++;
                }
                if (child >= k) {
                    break;
                }
                if (result[parent] > result[child]) {
                    result[parent] ^= result[child];
                    result[child] ^= result[parent];
                    result[parent] ^= result[child];
                    parent = child;
                } else {
                    break;
                }
            }
        }
    }

    // 将最小值置于堆顶
    public static void smallUp(int[] arr) {
        int length = arr.length;
        for (int i = (length - 2) / 2; i >= 0; i--) {
            int child = 2 * i + 1;
            if (child < length - 1 && arr[child] > arr[child + 1]) {
                child++;
            }
            if (arr[i] > arr[child]) {
                arr[i] ^= arr[child];
                arr[child] ^= arr[i];
                arr[i] ^= arr[child];
            }
        }
    }

    public static void buildHeap(int[] arr) {
        int length = arr.length;
        for (int j = 0; j < length; j++) {
            for (int i = (length - 2) / 2; i >= j; i--) {
                int child = 2 * i + 1;
                if (child < length - 1 && arr[child] > arr[child + 1]) {
                    child++;
                }
                if (arr[i] > arr[child]) {
                    arr[i] ^= arr[child];
                    arr[child] ^= arr[i];
                    arr[i] ^= arr[child];
                }
            }
        }
    }

    public static int partitionOfK(int[] arr, int start, int end, int k) {
        int low = start, high = end, key = arr[start];
        while (low < high) {
            while (low < high && arr[high] >= key) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= key) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = key;
        if (low < k) {
            return partitionOfK(arr, low + 1, end, k);
        } else if (low > k) {
            return partitionOfK(arr, start, low - 1, k);
        } else {
            return arr[low];
        }
    }

    public static void quickSort(int[] arr, int start, int end) {
        int low = start, high = end, key = arr[start];
        while (low < high) {
            while (low < high && arr[high] >= key) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= key) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = key;
        if (low - 1 > start) {
            quickSort(arr, start, low - 1);
        }
        if (high < end) {
            quickSort(arr, high + 1, end);
        }
    }

}
