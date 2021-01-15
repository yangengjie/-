package 多叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 */
public class _559_N叉树的最大深度 {
	int levelCount = 1;
	int height = 0;

	public int maxDepth2(Node root) {
		if (root == null)
			return height;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			levelCount--;
			for (int i = 0; i < node.children.size(); i++) {
				queue.offer(node.children.get(i));
			}
			if (levelCount == 0) {
				levelCount = queue.size();
				height++;
			}
		}

		return height;
	}

	public int maxDepth(Node root) {
		return height(root);
	}

	private int height(Node node) {
		if (node == null)
			return 0;

		int height = 0;
		for (Node child : node.children) {
			height = Math.max(height, height(child));
		}
		return height + 1;
	}
}
