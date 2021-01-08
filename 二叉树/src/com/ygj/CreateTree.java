package com.ygj;

/**
 * 构建二叉树
 * @author glen
 *
 */
public class CreateTree {

	// 已知前序加中序 输出后序
	// 前序遍历的顺序是: CABGHEDF
	// 中序遍历的顺序是: GBHACDEF
	public static Node preAndMid(String preStr, String midStr) {
		if (preStr == null || preStr.length() == 0 || midStr == null || midStr.length() == 0)
			return null;
		// 获取根节点
		char rootElement = preStr.charAt(0);
		Node root = new Node(String.valueOf(rootElement), null, null);
		if (midStr.length() == 1) //表示只有一个根节点
			return root;
		// 获取根节点的值在中序遍历结果中的位置
		int index = midStr.indexOf(rootElement);

		String leftChildStr = midStr.substring(0, index);
		if (leftChildStr.isEmpty()) { // 表示没有左子树
			root.left = null;
		} else {
			root.left = preAndMid(preStr.substring(1, index + 1), leftChildStr);
		}

		String rightChildStr = midStr.substring(index + 1);
		if (rightChildStr.isEmpty()) { // 表示没有右子树
			root.right = null;
		} else {
			root.right = preAndMid(preStr.substring(index + 1), rightChildStr);
		}
		return root;
	}

	// 已知后序加中序 输出前序
	// 后序遍历的顺序是: GHBADFEC
	// 中序遍历的顺序是: GBHACDEF
	public static Node postAndMid(String postStr, String midStr) {
		if (postStr == null || postStr.length() == 0 || midStr == null || midStr.length() == 0)
			return null;
		// 获取根节点
		char rootElement = postStr.charAt(postStr.length()-1);
		Node root = new Node(String.valueOf(rootElement), null, null);
		if (midStr.length() == 1)
			return root;
		// 获取根节点的值在中序遍历结果中的位置
		int index = midStr.indexOf(rootElement);

		String leftChildStr = midStr.substring(0, index);
		if (leftChildStr.isEmpty()) { // 表示没有左子树
			root.left = null;
		} else {
			root.left = postAndMid(postStr.substring(0, index), leftChildStr);
		}

		String rightChildStr = midStr.substring(index + 1);
		if (rightChildStr.isEmpty()) { // 表示没有右子树
			root.right = null;
		} else {
			root.right = postAndMid(postStr.substring(index ,postStr.length()-1), rightChildStr);
		}
		
		return root;
	}

	public static void postOrder(Node node) {
		if (node == null)
			return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.element);
	}
	public static void preOrder(Node node) {
		if (node == null)
			return;
		System.out.println(node.element);
		preOrder(node.left);
		preOrder(node.right);
	}

	private static class Node {
		Node left;
		Node right;
		String element;

		public Node(String element, Node left, Node right) {
			this.left = left;
			this.right = right;
			this.element = element;
		}
	}

	public static void main(String[] args) {
//		Node rootNode = preAndMid("CABGHEDF", "GBHACDEF");
//		postOrder(rootNode);
		
		preOrder(postAndMid("GHBADFEC", "GBHACDEF"));
		
	}

}
