package one;

public class GreatestCommonDivisor {
	public static int gcb(int a, int b) {
		if(b==0) {
			return a;
		}
		int i = a % b;
		return gcb(b, i);
	}
	
	public static void main(String[] args) {
		System.out.println(gcb(14, 10));
	}
}
