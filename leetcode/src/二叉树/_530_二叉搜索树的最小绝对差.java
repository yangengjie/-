package 二叉树;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 */
public class _530_二叉搜索树的最小绝对差 {
	private int minValue = Integer.MAX_VALUE;
	private TreeNode lastNode;

	public int getMinimumDifference(TreeNode root) {
		if (root == null)
			return minValue;

		getMinimumDifference(root.left);

		if (lastNode != null)
			minValue = Math.min(minValue, root.val - lastNode.val);

		lastNode = root;

		getMinimumDifference(root.right);

		return minValue;

	}

	public int getMinimumDifference2(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		while (true) {
			if (node != null) {
				stack.add(node);
				node = node.left;
			} else {
				if (stack.isEmpty())
					break;
				node = stack.pop();
				if (lastNode != null)
					minValue = Math.min(minValue, node.val - lastNode.val);
				lastNode = node;
				node = node.right;
			}

		}
		return minValue;
	}
}
