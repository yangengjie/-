package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 */
public class _662_二叉树最大宽度 {

	public int widthOfBinaryTree(TreeNode root) {
		Queue<AnnotatedNode> queue = new LinkedList<AnnotatedNode>();
		queue.add(new AnnotatedNode(root, 0, 0));
		int curDepth = 0, left = 0, maxWidth = 0;

		while (!queue.isEmpty()) {
			AnnotatedNode a = queue.poll();
			if (a.node != null) {
				if (a.node.left != null)
					queue.offer(new AnnotatedNode(a.node.left, a.depth + 1, a.pos * 2));
				if (a.node.right != null)
					queue.offer(new AnnotatedNode(a.node.right, a.depth + 1, a.pos * 2 + 1));
				if (curDepth != a.depth) {
					curDepth = a.depth;
					left = a.pos;
				}
				maxWidth = Math.max(maxWidth, a.pos - left + 1);
			}
		}
		return maxWidth;

	}

	class AnnotatedNode {
		TreeNode node;
		int depth, pos;

		AnnotatedNode(TreeNode n, int d, int p) {
			node = n;
			depth = d;
			pos = p;
		}
	}
}
