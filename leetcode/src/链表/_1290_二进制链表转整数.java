package 链表;

//https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/
public class _1290_二进制链表转整数 {
	public int getDecimalValue(ListNode head) {
		StringBuilder sb = new StringBuilder();
		while (head != null) {
			sb.append(head.val);
			head = head.next;
		}
		return Integer.parseInt(sb.toString(), 2);
	}
}
