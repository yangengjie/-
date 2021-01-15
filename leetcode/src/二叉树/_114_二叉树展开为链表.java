package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 */
public class _114_二叉树展开为链表 {
	public void flatten(TreeNode root) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		preorderTraversal(root, list);
		TreeNode node = root;
		for (int i = 1; i < list.size(); i++) {
			TreeNode curNode = list.get(i);
			node.right = curNode;
			node.left = null;
			node = curNode;
		}
	}

	private void preorderTraversal(TreeNode root, List<TreeNode> list) {
		if (root == null)
			return;
		list.add(root);
		preorderTraversal(root.left, list);
		preorderTraversal(root.right, list);
	}
}
