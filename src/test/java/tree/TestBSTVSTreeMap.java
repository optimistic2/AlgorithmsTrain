package tree;

import org.junit.Test;
import tree.bstAVL.AVLMap;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by biyanchen on 2020/3/3.
 */
public class TestBSTVSTreeMap {
    public static final int MAX = 65535;
    //    public static final int MAX = 2040;
    private Random random = new Random();

    @Test
    public void testBSTRandom() {
        AVLMap<Integer, String> map = new AVLMap<>();
        for (int i = 0; i < MAX; i++) {
            Integer key = random.nextInt(MAX);
            map.put(key, key + "");
        }
        map.checkBalance();
        for (int i = 0; i < MAX; i++) {
            map.get(random.nextInt(MAX));
        }
    }

    @Test
    public void testTreeMapRandom() {
        Map<Integer, String> map = new TreeMap<>();
        for (int i = 0; i < MAX; i++) {
            Integer key = random.nextInt(MAX);
            map.put(key, key + "");
        }
        for (int i = 0; i < MAX; i++) {
            map.get(random.nextInt(MAX));
        }
    }

    @Test
    public void testBSTIncrement() {
        AVLMap<Integer, String> map = new AVLMap<>();
        for (int i = 0; i < MAX; i++) {
            Integer key = i;
            map.put(key, key + "");
        }
        map.checkBalance();
        for (int i = 0; i < MAX; i++) {
            map.get(random.nextInt(MAX));
        }
    }

    @Test
    public void testTreeMapIncrement() {
        Map<Integer, String> map = new TreeMap<>();
        for (int i = 0; i < MAX; i++) {
            Integer key = i;
            map.put(key, key + "");
        }
        for (int i = 0; i < MAX; i++) {
            map.get(random.nextInt(MAX));
        }

    }

}