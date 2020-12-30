package 链表;

//https://leetcode-cn.com/problems/swap-nodes-in-pairs/
public class _24_两两交换链表中的节点 {
	public ListNode swapPairs(ListNode head) {
		ListNode pre = new ListNode();
        pre.next = head;
        ListNode temp = pre;
        while (head != null && head.next != null) {
            ListNode start = head;
            ListNode end = head.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
	}
}
