package ����;

//https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
public class _83_ɾ�����������е��ظ�Ԫ�� {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode temp = head.next;
		ListNode newHead = head;
		while (temp != null) {
			if (newHead.val == temp.val) {
				newHead.next = temp.next;
			} else {
				newHead = newHead.next;
			}
			temp = newHead.next;
		}
		return head;
	}
}
