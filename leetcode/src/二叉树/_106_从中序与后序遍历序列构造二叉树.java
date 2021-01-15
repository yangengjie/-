package 二叉树;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class _106_从中序与后序遍历序列构造二叉树 {
	public  TreeNode buildTree(int[] inorder, int[] postorder) {
		int inorderLength = inorder.length;
		int postorderLength = postorder.length;
		if (inorder == null || postorder == null || inorderLength == 0 || postorder.length == 0)
			return null;
		int rootVal = postorder[postorderLength - 1];
		TreeNode rooTreeNode = new TreeNode(rootVal);

		int index = getIndex(rootVal, inorder);

		int[] leftArray = Arrays.copyOfRange(inorder, 0, index);

		if (leftArray.length == 0) {
			rooTreeNode.left = null;
		} else {
			rooTreeNode.left = buildTree(leftArray, Arrays.copyOfRange(postorder, 0, index));
		}

		int[] rightArray = Arrays.copyOfRange(inorder, index + 1, postorderLength);
		if (rightArray.length == 0) {
			rooTreeNode.right = null;
		} else {
			rooTreeNode.right = buildTree(rightArray, Arrays.copyOfRange(postorder, index, postorder.length - 1));
		}
		return rooTreeNode;
	}

	private  int getIndex(int val, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (val == array[i])
				return i;
		}
		return -1;
	}
	
}
