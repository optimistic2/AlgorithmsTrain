package tree.bstAVL;

import java.util.Map;

/**
 * Created by biyanchen on 2020/2/13.
 */
public class BSTEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;
    private BSTEntry<K, V> left;
    private BSTEntry<K, V> right;

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

    public BSTEntry(K key, V value, BSTEntry<K, V> left, BSTEntry<K, V> right) {
        super();
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BSTEntry(K key, V value) {
        super();
        this.key = key;
        this.value = value;
    }

    public BSTEntry(K key) {
        super();
        this.key = key;
    }

    public BSTEntry<K, V> getLeft() {
        return left;
    }

    public void setLeft(BSTEntry<K, V> left) {
        this.left = left;
    }

    public BSTEntry<K, V> getRight() {
        return right;
    }

    public void setRight(BSTEntry<K, V> right) {
        this.right = right;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "BSTEntry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
