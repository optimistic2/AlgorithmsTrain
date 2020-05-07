package tree.bstAVL;

import tree.TreeNode;

/**
 * Created by biyanchen on 2020/3/1.
 */
public class InorderSuccessorInBST {
    public TreeNode InorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (p == getLastEntry(root)) {
            return null;
        }
        if (p.getRight() != null) {
            return getFirstEntry(p.getRight());
        }
        TreeNode parent = root;
        TreeNode temp = root;
        while (parent != null) {
            if (parent == p) {
                break;
            } else if (parent.getVal() > p.getVal()) {
                temp = parent;
                parent = parent.getLeft();
            } else {
                parent = parent.getRight();
            }
        }
        return temp;
    }

    public TreeNode getFirstEntry(TreeNode p) {
        if (p == null) {
            return null;
        }
        while (p.getLeft() != null) {
            p = p.getLeft();
        }
        return p;
    }

    public TreeNode getLastEntry(TreeNode p) {
        if (p == null) {
            return null;
        }
        while (p.getRight() != null) {
            p = p.getRight();
        }
        return p;
    }

}
