package warmUp;

/**
 * @author biyanchen
 * @date 2021/6/2 12:12 下午
 */
public class MergeTwoList {

    public static class ListNode {

        ListNode(int val) {
            this.val = val;
        }

        int val;
        ListNode next = null;

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(5);
        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(6);
        System.out.println(mergeTwoLists5(l1, l2));
    }

    /**
     * @param l1 ListNode类
     * @param l2 ListNode类
     * @return ListNode类
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write code here

        ListNode result = new ListNode(0);
        ListNode r = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ListNode temp = new ListNode(l1.val);
                result.next = temp;
                result = temp;
                l1 = l1.next;
            } else {
                ListNode temp = new ListNode(l2.val);
                result.next = temp;
                result = temp;
                l2 = l2.next;
            }
        }
        while (l1 != null) {
            ListNode temp = new ListNode(l1.val);
            result.next = temp;
            result = temp;
            l1 = l1.next;
        }
        while (l2 != null) {
            ListNode temp = new ListNode(l2.val);
            result.next = temp;
            result = temp;
            l2 = l2.next;
        }
        return r.next;
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // write code here
        ListNode result = new ListNode(0);
        ListNode r = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ListNode temp = new ListNode(l1.val);
                result.next = temp;
                result = temp;
                l1 = l1.next;
            } else {
                ListNode temp = new ListNode(l2.val);
                result.next = temp;
                result = temp;
                l2 = l2.next;
            }
        }
        result.next = l1 != null ? l1 : l2;
        return r.next;
    }

    public static ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists3(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists3(l1, l2.next);
            return l2;
        }
    }

    public static ListNode mergeTwoLists4(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode r = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                r.next = l1;
                l1 = l1.next;
            } else {
                r.next = l2;
                l2 = l2.next;
            }
            r = r.next;
        }
        r.next = l1 != null ? l1 : l2;
        return result.next;
    }
    
    public static ListNode mergeTwoLists5(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode r = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                result.next = l1;
                l1 = l1.next;
            } else {
                result.next = l2;
                l2 = l2.next;
            }
            result = result.next;
        }
        result.next = l1 != null ? l1 : l2;
        return r.next;
    }
}
