package ����;

//https://leetcode-cn.com/problems/middle-of-the-linked-list/
public class _876_������м��� {
	public ListNode middleNode(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow=slow.next;
			fast=fast.next.next;
		}
		return slow;
	}
}
