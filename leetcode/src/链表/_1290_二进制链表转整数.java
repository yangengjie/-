package ����;

//https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/
public class _1290_����������ת���� {
	public int getDecimalValue(ListNode head) {
		StringBuilder sb = new StringBuilder();
		while (head != null) {
			sb.append(head.val);
			head = head.next;
		}
		return Integer.parseInt(sb.toString(), 2);
	}
}
