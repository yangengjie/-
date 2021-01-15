package 二叉树;

/**
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 */
public class _701_二叉搜索树中的插入操作 {
	public TreeNode insertIntoBST(TreeNode root, int val) {
		if (root == null)
			return new TreeNode(val);

		TreeNode node = root;
		TreeNode parentNode = root;
		int cmp = 0;
		while (node != null) {
			cmp = compare(node, val);
			parentNode = node;
			if (cmp > 0)
				node = node.right;
			else
				node = node.left;
		}
		if (cmp > 0)
			parentNode.right = new TreeNode(val);
		else
			parentNode.left = new TreeNode(val);
		return root;
	}

	private int compare(TreeNode root, int val) {
		return val - root.val;
	}

}
