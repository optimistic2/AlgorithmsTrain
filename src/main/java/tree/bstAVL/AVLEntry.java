package tree.bstAVL;

import java.util.Map;

/**
 * 红黑树特性
 * 1. 每个节点为黑色或红色
 * 2. 根节点为黑色
 * 3. 每个叶子节点（nil）是黑色
 * 4. 如果一个节点是红色的则它的子节点必为黑色
 * 5. 从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点
 *
 * Created by biyanchen on 2020/2/13.
 */
public class AVLEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;
    private AVLEntry<K, V> left;
    private AVLEntry<K, V> right;
    private int height = 1;

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLEntry(K key, V value, AVLEntry<K, V> left, AVLEntry<K, V> right) {
        super();
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public AVLEntry(K key, V value) {
        super();
        this.key = key;
        this.value = value;
    }

    public AVLEntry(K key) {
        super();
        this.key = key;
    }

    public AVLEntry<K, V> getLeft() {
        return left;
    }

    public void setLeft(AVLEntry<K, V> left) {
        this.left = left;
    }

    public AVLEntry<K, V> getRight() {
        return right;
    }

    public void setRight(AVLEntry<K, V> right) {
        this.right = right;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "AVLEntry{" +
                "key=" + key +
                ", value=" + value +
                ", left=" + left +
                ", right=" + right +
                ", height=" + height +
                '}';
    }
}
