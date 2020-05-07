package tree.bstAVL;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by biyanchen on 2020/2/14.
 */
public class AVLIterator<K, V> implements Iterator<AVLEntry<K, V>> {
    private Stack<AVLEntry<K, V>> stack;

    public AVLIterator(AVLEntry<K, V> root) {
        super();
        stack = new Stack<>();
        addLeftPath(root);
    }

    private void addLeftPath(AVLEntry<K, V> p) {
        while (p != null) {
            stack.push(p);
            p = p.getLeft();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public AVLEntry<K, V> next() {
        AVLEntry<K, V> p = stack.pop();
        addLeftPath(p.getRight());
        return p;
    }

    @Override
    public void remove() {
        throw new ConcurrentModificationException("Can not remove!");
    }
}
