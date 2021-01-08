package ������;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.xml.soap.Node;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/submissions/
 *
 */
public class _94_��������������� {
	private List<Integer> list = new ArrayList<Integer>();

	// �ݹ鷽ʽʵ��
	public List<Integer> inorderTraversal(TreeNode root) {
		if (root == null)
			return list;

		inorderTraversal(root.left);
		list.add(root.val);
		inorderTraversal(root.right);

		return list;
	}

	// ������ʽʵ��
	public List<Integer> inorderTraversal2(TreeNode root) {
		if (root == null)
			return list;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		while (true) {
			if (node != null) {
				stack.add(node);
				node = node.left;
			} else {
				if (stack.isEmpty())
					return list;
				node = stack.pop();
				list.add(node.val);
				node = node.right;
			}
		}
	}
}
