package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class _98_验证二叉搜索树 {
	private double inorder = -Double.MAX_VALUE;

	public boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;
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
				if (inorder >= node.val)
					return false;
				inorder = node.val;
				node = node.right;
			}

		}
		return true;
	}

}
