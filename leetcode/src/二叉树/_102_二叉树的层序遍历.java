package ¶þ²æÊ÷;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class _102_¶þ²æÊ÷µÄ²ãÐò±éÀú {
	private List<List<Integer>> rootList = new ArrayList<List<Integer>>();

	public List<List<Integer>> levelOrder(TreeNode root) {
		if (root == null)
			return rootList;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int levelCount = 1;
		List<Integer> list = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			levelCount--;
			list.add(node.val);
			if (node.left != null)
				queue.offer(node.left);
			if (node.right != null)
				queue.offer(node.right);
			if (levelCount == 0) {
				levelCount = queue.size();
				rootList.add(list);
				list = new ArrayList<Integer>();
			}
		}
		return rootList;
	}
}
