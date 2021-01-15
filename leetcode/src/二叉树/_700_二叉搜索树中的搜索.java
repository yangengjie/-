package ¶þ²æÊ÷;

/**
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 */
public class _700_¶þ²æËÑË÷Ê÷ÖÐµÄËÑË÷ {
	public TreeNode searchBST(TreeNode root, int val) {
		if (root == null)
			return null;
		while (root != null) {
			if (val > root.val) {
				root = root.right;
			} else if (val < root.val) {
				root = root.left;
			} else {
				return root;
			}
		}
		return null;
	}

}
