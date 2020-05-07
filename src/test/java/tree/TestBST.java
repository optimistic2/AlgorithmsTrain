package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.bstAVL.AVLEntry;
import tree.bstAVL.AVLMap;
import tree.bstAVL.BSTEntry;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by biyanchen on 2020/2/14.
 */
public class TestBST {
    private Random random = new Random();
    private final int MAX1 = 16;
    private final int MAX2 = 65535;

    @Test
    public void putAndItr() {
        AVLMap<Integer, String> map = new AVLMap<>();
        for (int i = 0; i < MAX1; i++) {
            int key = random.nextInt(MAX1);
            System.out.println("key:" + key);
            map.put(key, key + "");
        }
        map.checkBalance();
        Iterator<BSTEntry<Integer, String>> iterator = map.iterator();
        if (iterator != null) {
            while (iterator.hasNext()) {
                System.out.println(iterator.next().getKey());
            }
            System.out.println();
        }
    }

    @Test
    public void putAndItrWithJDK() {
        AVLMap<Integer, String> map1 = new AVLMap<>();
        Map<Integer, String> map2 = new TreeMap<>();
        for (int i = 0; i < MAX2; i++) {
            int key = random.nextInt(MAX2);
            map1.put(key, key + "");
            map2.put(key, key + "");
        }
        Assert.assertTrue(map1.size() == map2.size());
        System.out.println("size:" + map1.size());
        Iterator<BSTEntry<Integer, String>> iterator1 = map1.iterator();
        Iterator<Map.Entry<Integer, String>> iterator2 = map2.entrySet().iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            Assert.assertTrue(iterator1.next().getKey().equals(iterator2.next().getKey()));
        }

        Assert.assertTrue(!iterator1.hasNext() && !iterator2.hasNext());

    }

    @Test
    public void testQuery() {
        AVLMap<Integer, String> map = new AVLMap<>();
        map.put(4, "a");
        map.put(2, "b");
        map.put(5, "c");
        map.put(1, "d");
        map.put(3, "e");
        map.put(5, "f");
        map.put(1, "g");
        Assert.assertTrue(map.get(4).equals("a"));
        Assert.assertTrue(map.get(5).equals("f"));
        Assert.assertTrue(map.get(7) == null);
        Assert.assertTrue(map.containsKey(1));
        Assert.assertFalse(map.containsKey(-1));
        Assert.assertTrue(map.get(4).equals("a"));
        Assert.assertTrue(map.containsValue("a"));
        Assert.assertFalse(map.containsValue("ggg"));
    }

    @Test
    public void testQueryWithJDK() {
        AVLMap<Integer, String> map1 = new AVLMap<>();
        Map<Integer, String> map2 = new TreeMap<>();
        for (int i = 0; i < MAX2; i++) {
            int key = random.nextInt(MAX2);
            String value = key + "PP";
            map1.put(key, value);
            map2.put(key, value);
        }
        for (int i = 0; i < MAX2; i++) {
            int key = random.nextInt(MAX2);
            Assert.assertTrue(map1.containsKey(key) == map2.containsKey(key));
            if (map1.get(key) == null) {
                Assert.assertTrue(map2.get(key) == null);
            } else {
                Assert.assertTrue(map1.get(key).equals(map2.get(key)));
            }
        }
//        for (int i = 0; i < 255; i++) {
//            String value = random.nextInt(MAX2) + "PP";
//            Assert.assertTrue(map1.containsValue(value) == map2.containsValue(value));
//
//        }
    }

    @Test
    public void testRemoveCase1() {
        AVLMap<Integer, String> map = new AVLMap<>();
//        int[] array = {5, 2, 6, 1, 4, 7, 3};
        int[] array = {6, 2, 7, 1, 4, 8, 3, 5};
        for (int i : array) {
            map.put(i, i + "");
        }
        System.out.println(map.remove(2));
        map.levelOrder();
        Iterator<BSTEntry<Integer, String>> iterator = map.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().getKey() + " ");
        }
        System.out.println();
    }

    @Test
    public void testRemoveWithJDK() {
        AVLMap<Integer, String> map1 = new AVLMap<>();
        Map<Integer, String> map2 = new TreeMap<>();
        for (int i = 0; i < MAX2; i++) {
            int key = random.nextInt(MAX2);
            map1.put(key, key + "");
            map2.put(key, key + "");
        }
        System.out.println("size:" + map1.size());
        for (int i = 0; i < MAX2 >>> 1; i++) {
            int k = random.nextInt(MAX2);
            if (map1.containsKey(k)) {
                Assert.assertTrue(map1.remove(k).equals(map2.remove(k)));
            } else {
                Assert.assertTrue(map1.remove(k) == null && map2.remove(k) == null);
            }

        }
        map1.checkBalance();

        Iterator<AVLEntry<Integer, String>> iterator1 = map1.iterator();
        Iterator<Map.Entry<Integer, String>> iterator2 = map2.entrySet().iterator();

        System.out.println("size:" + map1.size());

        Assert.assertTrue(map1.size() == map2.size());
    }

    @Test
    public void testAVLDelete() {
        AVLMap<Integer, String> map1 = new AVLMap<>();
        Map<Integer, String> map2 = new TreeMap<>();
        for (int i = 0; i < MAX2; i++) {
            int key = random.nextInt(MAX2);
            map1.put(key, key + "");
            map2.put(key, key + "");
            System.out.println("input:" + key);
        }
        map1.checkBalance();
        System.out.println("size1:" + map1.size() + " size2:" + map2.size());
        for (int i = 0; i < MAX2 >>> 1; i++) {
            int k = random.nextInt(MAX2);
            map1.remove(k);

            map2.remove(k);
            System.out.println("k:" + k + " size1:" + map1.size() + " size2:" + map2.size());
        }
        map1.checkBalance();
        System.out.println("size1:" + map1.size() + " size2:" + map2.size());
        Assert.assertTrue(map1.size() == map2.size());
        Iterator<AVLEntry<Integer, String>> iterator1 = map1.iterator();
        Iterator<Map.Entry<Integer, String>> iterator2 = map2.entrySet().iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            Assert.assertTrue(iterator1.next().getKey().equals(iterator2.next().getKey()));
        }
        System.out.println("size1:" + map1.size() + " size2:" + map2.size());

        Assert.assertTrue(!iterator1.hasNext() && !iterator2.hasNext());
    }

}
