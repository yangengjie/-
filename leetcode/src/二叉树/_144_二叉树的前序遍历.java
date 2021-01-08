package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class _144_二叉树的前序遍历 {
	private List<Integer> list = new ArrayList<Integer>();

	// 递归方式实现
	public List<Integer> preorderTraversal(TreeNode root) {
		if (root == null)
			return list;

		list.add(root.val);
		preorderTraversal(root.left);
		preorderTraversal(root.right);

		return list;
	}

	// 迭代方式实现 使用栈来实现
	public List<Integer> preorderTraversal2(TreeNode root) {
		if (root == null)
			return list;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			list.add(node.val);
			if (node.right != null)
				stack.add(node.right);

			if (node.left != null)
				stack.add(node.left);
		}

		return list;
	}
}
