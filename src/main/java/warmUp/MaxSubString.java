package warmUp;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;

/**
 * @author biyanchen
 * @date 2021/6/1 10:49 上午
 */
public class MaxSubString {

    public static void main(String[] args) {
        System.out.println(getMaxSubString("a1f1111b21a"));
    }

    public static String getMaxSubString(String str) {
        int length = str.length();
        Map<Character, Integer> map = Maps.newHashMap();
        int start = 0, end = 0, start2 = 0, end2 = 0;
        for (int i = 0; i < length; i++) {
            Integer val;
            if (Objects.isNull(val = map.get(str.charAt(i)))) {
                end = i;
            } else {
                if (end - start > end2 - start2) {
                    end2 = end;
                    start2 = start;
                }
                if (val >= start) {
                    start = val + 1;
                } else {
                    end = i;
                }
            }
            map.put(str.charAt(i), i);
        }
        if (end - start > end2 - start2) {
            end2 = end;
            start2 = start;
        }
        return str.substring(start2, end2 + 1);
    }
}
