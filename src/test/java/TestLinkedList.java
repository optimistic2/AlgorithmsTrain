import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by biyanchen on 2020/1/8.
 */
public class TestLinkedList {
    @Test
    public void testQuery() {
        List<Integer> list = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        System.out.println(list.get(3));
        System.out.println(list.get(8));
    }

    @Test
    public void test() {
        Integer[] array = {1};
//        String[] array = {"1", "2", "3"};
        System.out.println(getMessage("{}--{}--", array));
    }


    public String getMessage(String message, Object... args) {
        if (message != null && args != null && args.length > 0) {
            message = StringUtils.replace(message, "{}", "%s", args.length);
            return String.format(message, args);
        }
        return message;
    }

}