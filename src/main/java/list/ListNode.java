package list;

/**
 * Created by biyanchen on 2019/2/11.
 */
public class ListNode {
    int val;

    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);

        a.next = b;
        b.next = c;
        c.next = d;
        ListNode result = reverse2(a);
        
        System.out.println("\n");
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode reverse2(ListNode a) {
        if (a == null) {
            return a;
        }
        ListNode temp = a;
        ListNode next;
        ListNode pre = null;
        while (temp != null) {
            next = temp.next;
            temp.next = pre;
            pre = temp;
            temp = next;
        }
        return pre;

    }

    public ListNode reverse(ListNode a) {
        ListNode result = null;
        ListNode pNode = a;
        ListNode pPrev = null;

        while (pNode != null) {
            System.out.println(pNode.val);
            ListNode pNext = pNode.next;
            if (pNext == null) {
                result = pNode;
            }
            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        
        return result;
    }
}
