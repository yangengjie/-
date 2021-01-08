package ������;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class _145_�������ĺ������ {
	List<Integer> list = new ArrayList<Integer>();

	// �ݹ�ʵ��
	public List<Integer> postorderTraversal(TreeNode root) {
		if (root == null)
			return list;

		postorderTraversal(root.left);
		postorderTraversal(root.right);
		list.add(root.val);
		return list;
	}
	
}
