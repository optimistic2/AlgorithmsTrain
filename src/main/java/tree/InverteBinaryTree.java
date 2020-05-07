package tree;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by biyanchen on 2020/1/19.
 */
public class InverteBinaryTree {


    public TreeNode inverteTree_xian(TreeNode root) {
        if (root != null) {
            TreeNode t = root.getLeft();
            root.setLeft(root.getRight());
            root.setRight(t);
            inverteTree_xian(root.getLeft());
            inverteTree_xian(root.getRight());
            return root;
        }
        return null;
    }

    public TreeNode inverteTree_xian2(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode p = stack.pop();
                TreeNode t = p.getLeft();
                p.setLeft(p.getRight());
                p.setRight(t);
                if (p.getLeft() != null) {
                    stack.add(p.getLeft());
                }
                if (p.getRight() != null) {
                    stack.add(p.getRight());
                }
            }
            return root;
        }
        return null;
    }

    public TreeNode inverteTree_zhong(TreeNode root) {
        if (root != null) {
            inverteTree_zhong(root.getLeft());
            TreeNode t = root.getLeft();
            root.setLeft(root.getRight());
            root.setRight(t);
            inverteTree_zhong(root.getLeft());
            return root;
        }
        return null;
    }

    public TreeNode inverteTree_zhong2(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack();
            TreeNode p = root;
            while (p != null) {
                stack.push(p);
                p = p.getLeft();
            }
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                TreeNode t = node.getLeft();
                node.setLeft(node.getRight());
                node.setRight(t);
                if (node.getRight() != null) {
                    stack.push(node.getRight());
                }
            }
            return root;
        }
        return null;
    }

    public TreeNode inverteTree_hou(TreeNode root) {
        if (root != null) {
            inverteTree_hou(root.getLeft());
            inverteTree_hou(root.getRight());
            TreeNode t = root.getLeft();
            root.setLeft(root.getRight());
            root.setRight(t);
            return root;
        }
        return null;
    }

    public TreeNode inverteTree_hou2(TreeNode root) {
        int left = 1;
        int right = 2;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> stackFlag = new Stack<>();
        TreeNode q = root;
        while (q != null || !stack.empty()) {
            while (q != null) {
                stack.push(q);
                stackFlag.push(left);
                q = q.getLeft();
            }
            while (!stack.empty() && stackFlag.peek() == right) {
                stackFlag.pop();
                TreeNode node = stack.pop();
                TreeNode p = node.getLeft();
                node.setLeft(node.getRight());
                node.setRight(p);
            }
            if (!stack.empty() && stackFlag.peek() == left) {
                stackFlag.pop();
                stackFlag.push(right);
                q = stack.peek().getRight();
            }
        }
        return root;
    }

    public TreeNode inverteTree_ceng(TreeNode root) {
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode p = queue.poll();
                TreeNode t = p.getLeft();
                p.setLeft(p.getRight());
                p.setRight(t);
                if (null != p.getLeft()) {
                    queue.offer(p.getLeft());
                }
                if (null != p.getRight()) {
                    queue.offer(p.getRight());
                }
            }

            return root;
        }
        return null;
    }

}
