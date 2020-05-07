package leetcode._108;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * Created by biyanchen on 2020/2/13.
 */
public class Solution {
    private TreeNode root;
    private LinkedList<TreeNode> stack = new LinkedList<>();

    public void put(int value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            TreeNode p = root;
            while (p != null) {
                stack.push(p);
                int compareResult = value - p.val;
                if (compareResult == 0) {
                    p.val = (value);
                    break;
                } else if (compareResult < 0) {
                    if (p.left == null) {
                        p.left = (new TreeNode(value));
                        stack.push(p.left);
                        break;
                    } else {
                        p = p.left;
                    }
                } else {
                    if (p.right == null) {
                        p.right = (new TreeNode(value));
                        stack.push(p.right);
                        break;
                    } else {
                        p = p.right;
                    }
                }
            }
            fixAfterInsertion(value);
        }
    }

    private Map<TreeNode, Integer> heightMap = new HashMap<>();

    public int getHeight(TreeNode p) {
        return heightMap.containsKey(p) ? heightMap.get(p) : 0;
    }

    private TreeNode rotateRight(TreeNode p) {
        TreeNode left = p.left;
        p.left = (left.right);
        left.right = (p);
        heightMap.put(p, Math.max(getHeight(p.left), getHeight(p.right)) + 1);
        heightMap.put(left, Math.max(getHeight(left.left), getHeight(left.right)) + 1);
        return left;
    }

    private TreeNode rotateLeft(TreeNode p) {
        TreeNode right = p.right;
        p.right = (right.left);
        right.left = (p);
        heightMap.put(p, Math.max(getHeight(p.left), getHeight(p.right)) + 1);
        heightMap.put(right, Math.max(getHeight(right.right), getHeight(right.right)) + 1);
        return right;
    }

    private TreeNode firstLeftThenRight(TreeNode p) {
        p.left = (rotateLeft(p.left));
        return rotateRight(p);
    }

    private TreeNode firstRightThenLeft(TreeNode p) {
        p.right = (rotateRight(p.right));
        return rotateLeft(p);
    }

    private void fixAfterInsertion(int key) {
        TreeNode p = root;
        while (!stack.isEmpty()) {
            p = stack.pop();
            int newHeight = (Math.max(getHeight(p.left), getHeight(p.right)) + 1);
            int currentHeight;
            if ((currentHeight = getHeight(p)) > 1 && currentHeight == newHeight) {
                stack.clear();
                return;
            }
            heightMap.put(p, newHeight);
            int d = getHeight(p.left) - getHeight(p.right);
            if (Math.abs(d) <= 1) {
                continue;
            } else {
                if (d == 2) {
                    if (key < p.left.val) {
                        p = rotateRight(p);
                    } else {
                        p = firstLeftThenRight(p);
                    }
                } else {
                    if (key > p.right.val) {
                        p = rotateLeft(p);
                    } else {
                        p = firstRightThenLeft(p);
                    }
                }
                if (!stack.isEmpty()) {
                    if (key > stack.peek().val) {
                        stack.peek().right = (p);
                    } else {
                        stack.peek().left = (p);
                    }
                }
            }
        }
        root = p;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        for (int num : nums) {
            put(num);
        }
        return this.root;
    }

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        new Solution().sortedArrayToBST(nums);
    }

    private TreeNode buildFromSorted(int lo, int hi, int[] nums) {

        if (hi < lo) return null;

        int mid = (lo + hi) >>> 1;

        TreeNode left = null;
        if (lo < mid)
            left = buildFromSorted(lo, mid - 1, nums);

        TreeNode middle = new TreeNode(nums[mid]);

        if (left != null) {
            middle.left = left;
        }

        if (mid < hi) {
            TreeNode right = buildFromSorted(mid + 1, hi, nums);
            middle.right = right;
        }

        return middle;
    }

}
