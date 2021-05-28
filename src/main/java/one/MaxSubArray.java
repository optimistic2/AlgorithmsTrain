package one;

/**
 * @author biyanchen
 * @date 2021/5/11 3:38 下午
 */
public class MaxSubArray {

    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static int maxSubArray2(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            sum = Math.max(sum + num, num);
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static int maxSubArray3(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0 && 0 < nums[i]) {
                start = i;
            }
            sum = Math.max(sum + nums[i], nums[i]);
            if (sum > ans) {
                end = i;
            }
            ans = Math.max(ans, sum);
        }
        System.out.println("start:" + start + " end:" + end);
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {9, 2, -2, -4, 9};
//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4, 66};
        System.out.println("answer:" + maxSubArray(nums));
    }
}
