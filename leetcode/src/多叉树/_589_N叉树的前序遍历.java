package 多叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 */
public class _589_N叉树的前序遍历 {

	private List<Integer> list = new ArrayList<Integer>();

	public List<Integer> preorder(Node root) {
		if (root == null)
			return list;
		list.add(root.val);
		for (int i = 0; i < root.children.size(); i++)
			preorder(root.children.get(i));
		return list;
	}

	public List<Integer> preorder2(Node root) {
		if (root == null)
			return list;
		Stack<Node> stack = new Stack<>();
		stack.add(root);
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			list.add(node.val);
			if (!node.children.isEmpty()) {
				for (int i = node.children.size() - 1; i >= 0; i--) {
					stack.add(node.children.get(i));
				}
			}
		}

		return list;
	}
}
