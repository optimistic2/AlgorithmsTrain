package tree.bstAVL;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by biyanchen on 2020/2/14.
 */
public class BSTIterator {
    private Iterator<Integer> iterator;

    public BSTIterator(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        iterator = list.iterator();
    }

    // 中序遍历
    private void inOrder(TreeNode p, List<Integer> list) {
        if (p != null) {
            inOrder(p.getLeft(), list);
            list.add(p.getVal());
            inOrder(p.getRight(), list);
        }
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public int next() {
        return iterator.next();
    }
}
