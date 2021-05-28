package warmUp;

import com.google.common.collect.Sets;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author biyanchen
 * @date 2021/3/31 10:54 上午
 */
public class FakeNum {

    public static void main(String[] args) {
//        List<P> lists = Lists.newArrayList(new P("苹果", 12),
//                new P("苹果", 22),
//                new P("苹果", 52),
//                new P("苹果", 102),
//                new P("香蕉", 22),
//                new P("香蕉", 52),
//                new P("香蕉", 102),
//                new P("烤肠", 222),
//                new P("表", 5222),
//                new P("大西瓜", 402),
//                new P("芒果", 22),
//                new P("煎饼", 33),
//                new P("手机", 1002),
//                new P("大西瓜", 70),
//                new P("芒果", 44),
//                new P("煎饼", 65),
//                new P("手机", 81),
//                new P("纸巾", 1)
//        );
//        lists.forEach(x -> System.out.println(
//                "商品名：" + x.getName() + " 价格：" + x.getPrice() + " 销量计算：" + getNum(x.getName(),
//                        x.getPrice())));
//        System.out.println(getNum("人热gdewq"));

        Set<Integer> set = Sets.newHashSet();

        for (int i = 0; i < 99999; i++) {
            int j;
            if (set.add(j = getNum(i))) {
                System.out.println("第" + i + "次遍历到：" + j);
            }
        }
    }

    final static int[] config = {3, 2, 1, 4, 2, 3, 1, 2};
    final static int max = 2000;
    final static int a = 60;

    static int getNum(String productionName, int originalPrice) {
        if (originalPrice > max) {
            return 0;
        }

        int hash = productionName.hashCode();
        return a / originalPrice + config[7 & (hash ^ (hash >>> 8))];
    }

    static int getNum(String productionName) {
        int hash = productionName.hashCode();
        return 7 & (hash ^ (hash >>> 8));
    }

    static int getNum(int hash) {
        return 7 & (hash ^ (hash >>> 8));
    }

    @Data
    @AllArgsConstructor
    static class P {

        private String name;
        private int price;
    }


}
