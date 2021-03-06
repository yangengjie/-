package ����;

/**
 * ����: 1->2->6->3->4->5->6, val = 6 
 * ���: 1->2->3->4->5
 * 
 */
public class _203_�Ƴ�����Ԫ�� {
	public ListNode removeElements(ListNode head, int val) {
		if (head == null)
			return head;

		ListNode newHead = new ListNode(0);
		newHead.next = head;

		ListNode prev = newHead;
		ListNode current = head;
		while (current != null) {
			if (current.val == val) {
				prev.next = current.next;
			}else {
				prev = current;
			}
			current = current.next;
		}

		return newHead.next;
	}
}
