package ����;
///https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
public class _237_ɾ�������еĽڵ� {

	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}
}
