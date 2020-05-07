package one;

public class BinarySearch {
	static int num = 0;

	public static int binarySearch(int[] arr, int a, int low, int high) {
		num++;
		int mid = (low + high) / 2;
		if (low <= high) {
			if (a > arr[mid]) {
				if(mid == ((mid+high)/2))
					mid++;
				return binarySearch(arr, a, mid, high);
			} else if (a < arr[mid]) {
				if(mid == ((mid+low)/2))
					mid--;
				return binarySearch(arr, a, low, mid);
			} else {
				return mid;
			}
		}
		return -1;
	}

	public static int binarySearch1(int[] arr, int a) {
		int low = 0;
		int high = arr.length;
		while(low<=high) {
			num++;
			int mid = (low+high)/2;
			if(a>arr[mid]) {
				low = mid+1;
			}else if(a<arr[mid]) {
				high = mid-1;
			}else {
				return mid;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] arr = {2, 3, 5, 33, 65, 83, 92, 532};
		int a = 32;
		System.out.println("二分查找结果：" + BinarySearch.binarySearch1(arr, a) + "  查找次数：" + num);
	}
}
