package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 */
public class _107_二叉树的层序遍历II {

	private List<List<Integer>> rootList = new ArrayList<List<Integer>>();
	private int levelCount = 1;

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		if (root == null)
			return rootList;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<Integer> list = new ArrayList<Integer>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			list.add(node.val);
			levelCount--;
			if (node.left != null)
				queue.offer(node.left);
			if (node.right != null)
				queue.offer(node.right);
			if (levelCount == 0) {
				levelCount = queue.size();
				rootList.add(0, list);
				list = new ArrayList<Integer>();
			}
		}
		return rootList;
	}
}
