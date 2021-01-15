package �����;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 *https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 */
public class _590_N�����ĺ������ {
	private List<Integer> list = new ArrayList<Integer>();

	public List<Integer> postorder(Node root) {
		if (root == null)
			return list;

		for (int i = 0; i < root.children.size(); i++) {
			postorder(root.children.get(i));
		}
		list.add(root.val);
		return list;
	}
}
