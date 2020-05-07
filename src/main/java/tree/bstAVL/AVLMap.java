package tree.bstAVL;

import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by biyanchen on 2020/2/13.
 */
public class AVLMap<K, V> implements Iterable {
    private int size;
    private AVLEntry<K, V> root;
    private Comparator<K> comparator;
    private LinkedList<AVLEntry<K, V>> stack = new LinkedList<>();

    private int compare(K a, K b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        }
        Comparable<K> comparable = (Comparable<K>) a;
        return comparable.compareTo(b);
    }

    public AVLMap(Comparator<K> comparator) {
        super();
        this.comparator = comparator;
    }

    public AVLMap() {
        super();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K key, V value) {
        if (root == null) {
            root = new AVLEntry<>(key, value);
            size++;
        } else {
            AVLEntry<K, V> p = root;
            while (p != null) {
                stack.push(p);
                int compareResult = compare(key, p.getKey());
                if (compareResult == 0) {
                    p.setValue(value);
                    break;
                } else if (compareResult < 0) {
                    if (p.getLeft() == null) {
                        p.setLeft(new AVLEntry(key, value));
                        stack.push(p.getLeft());
                        size++;
                        break;
                    } else {
                        p = p.getLeft();
                    }
                } else {
                    if (p.getRight() == null) {
                        p.setRight(new AVLEntry(key, value));
                        stack.push(p.getRight());
                        size++;
                        break;
                    } else {
                        p = p.getRight();
                    }
                }
            }
        }
        fixAfterInsertion(key);
        return value;
    }

    @Override
    public Iterator iterator() {
        return new AVLIterator<>(root);
    }

    private AVLEntry<K, V> getEntry(K key) {
        AVLEntry<K, V> p = root;
        while (p != null) {
            int compareResult = compare(key, p.getKey());
            if (compareResult == 0) {
                return p;
            } else if (compareResult > 0) {
                p = p.getRight();
            } else {
                p = p.getLeft();
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return null != getEntry(key);
    }

    public V get(K key) {
        AVLEntry<K, V> p;
        return (p = getEntry(key)) != null ? p.getValue() : null;
    }

    public boolean containsValue(V value) {
        if (value == null) {
            return false;
        }
        Iterator<AVLEntry<K, V>> iterator = this.iterator();
        while (iterator.hasNext()) {
            AVLEntry<K, V> p;
            if ((p = iterator.next()) != null && value.equals(p.getValue())) {
                return true;
            }
        }
        return false;
    }

    public AVLEntry<K, V> getFirstEntry(AVLEntry<K, V> p) {
        if (p == null) {
            return null;
        }
        while (p.getLeft() != null) {
            p = p.getLeft();
        }
        return p;
    }

    public AVLEntry<K, V> getLastEntry(AVLEntry<K, V> p) {
        if (p == null) {
            return null;
        }
        while (p.getRight() != null) {
            p = p.getRight();
        }
        return p;
    }

    private AVLEntry<K, V> deleteEntry(AVLEntry<K, V> p, K key) {
        if (p == null) {
            return null;
        }
        int compareResult = compare(key, p.getKey());
        if (compareResult == 0) {
            if (p.getLeft() == null && p.getRight() == null) {
                p = null;
            } else if (p.getLeft() != null && p.getRight() == null) {
                p = p.getLeft();
            } else if (p.getLeft() == null && p.getRight() != null) {
                p = p.getRight();
            } else {
                // 偶数判断
                if ((size & 1) == 0) {
                    AVLEntry<K, V> rightMin = getFirstEntry(p.getRight());
                    p.setKey(rightMin.getKey());
                    p.setValue(rightMin.getValue());
                    AVLEntry<K, V> newRight = deleteEntry(p.getRight(), p.getKey());
                    p.setRight(newRight);
                } else {
                    AVLEntry<K, V> leftMax = getLastEntry(p.getLeft());
                    p.setKey(leftMax.getKey());
                    p.setValue(leftMax.getValue());
                    AVLEntry<K, V> newLeft = deleteEntry(p.getLeft(), p.getKey());
                    p.setLeft(newLeft);
                }
            }
        } else if (compareResult < 0) {
            AVLEntry<K, V> newLeft = deleteEntry(p.getLeft(), key);
            p.setLeft(newLeft);
        } else {
            AVLEntry<K, V> newRight = deleteEntry(p.getRight(), key);
            p.setRight(newRight);
        }
        p = fixAfterDeletion(p);
        return p;
    }

    public int getHeight(AVLEntry<K, V> p) {
        return p == null ? 0 : p.getHeight();
    }

    private AVLEntry<K, V> rotateRight(AVLEntry<K, V> p) {
        AVLEntry<K, V> left = p.getLeft();
        p.setLeft(left.getRight());
        left.setRight(p);
        p.setHeight(Math.max(getHeight(p.getLeft()), getHeight(p.getRight())) + 1);
        left.setHeight(Math.max(getHeight(left.getLeft()), getHeight(left.getRight())) + 1);
        return left;
    }

    private AVLEntry<K, V> rotateLeft(AVLEntry<K, V> p) {
        AVLEntry<K, V> right = p.getRight();
        p.setRight(right.getLeft());
        right.setLeft(p);
        p.setHeight(Math.max(getHeight(p.getLeft()), getHeight(p.getRight())) + 1);
        right.setHeight(Math.max(getHeight(right.getLeft()), getHeight(right.getRight())) + 1);
        return right;
    }

    private AVLEntry<K, V> firstLeftThenRight(AVLEntry<K, V> p) {
        p.setLeft(rotateLeft(p.getLeft()));
        p = rotateRight(p);
        return p;
    }

    private AVLEntry<K, V> firstRightThenLeft(AVLEntry<K, V> p) {
        p.setRight(rotateRight(p.getRight()));
        p = rotateLeft(p);
        return p;
    }

    private void fixAfterInsertion(K key) {
        AVLEntry<K, V> p = root;
        while (!stack.isEmpty()) {
            p = stack.pop();
            int newHeight = (Math.max(getHeight(p.getLeft()), getHeight(p.getRight())) + 1);
            if (p.getHeight() > 1 && p.getHeight() == newHeight) {
                stack.clear();
                return;
            }
            p.setHeight(newHeight);
            int d = getHeight(p.getLeft()) - getHeight(p.getRight());
            if (Math.abs(d) <= 1) {
                continue;
            } else {
                if (d == 2) {
                    if (compare(key, p.getLeft().getKey()) < 0) {
                        p = rotateRight(p);
                    } else {
                        p = firstLeftThenRight(p);
                    }
                } else {
                    if (compare(key, p.getRight().getKey()) > 0) {
                        p = rotateLeft(p);
                    } else {
                        p = firstRightThenLeft(p);
                    }
                }
                if (!stack.isEmpty()) {
                    if (compare(key, stack.peek().getKey()) > 0) {
                        stack.peek().setRight(p);
                    } else {
                        stack.peek().setLeft(p);
                    }
                }
            }
        }
        root = p;
    }

    public AVLEntry<K, V> fixAfterDeletion(AVLEntry<K, V> p) {
        if (p == null) {
            return null;
        }
        p.setHeight(Math.max(getHeight(p.getRight()), getHeight(p.getLeft())) + 1);
        int d = getHeight(p.getLeft()) - getHeight(p.getRight());
        if (d == 2) {
            if (getHeight(p.getLeft().getLeft()) - getHeight(p.getLeft().getRight()) >= 0) {
                p = rotateRight(p);
            } else {
                p = firstLeftThenRight(p);
            }
        } else if (d == -2) {
            if (getHeight(p.getRight().getRight()) - getHeight(p.getRight().getLeft()) >= 0) {
                p = rotateLeft(p);
            } else {
                p = firstRightThenLeft(p);
            }
        }
        return p;
    }

    public void checkBalance() {
        postOrderCheckBalance(root);
    }

    public void postOrderCheckBalance(AVLEntry<K, V> p) {
        if (p != null) {
            postOrderCheckBalance(p.getLeft());
            postOrderCheckBalance(p.getRight());
            Assert.isTrue(Math.abs(getHeight(p.getLeft()) - getHeight(p.getRight())) <= 1);
        }
    }

    public V remove(K key) {
        AVLEntry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        V oldValue = entry.getValue();
        root = deleteEntry(root, key);
        size--;
        return oldValue;
    }

    public void levelOrder() {
        Queue<AVLEntry> queue = new LinkedList<>();
        queue.offer(root);
        int preCount = 1;
        int pCount = 0;
        while (!queue.isEmpty()) {
            preCount--;
            AVLEntry<K, V> p = queue.poll();
            System.out.print(p + " ");
            if (p.getLeft() != null) {
                queue.offer(p.getLeft());
                pCount++;
            }
            if (p.getRight() != null) {
                queue.offer(p.getRight());
                pCount++;
            }
            if (preCount == 0) {
                preCount = pCount;
                pCount = 0;
                System.out.println();
            }
        }
    }
}
