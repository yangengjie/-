package com.ygj;

/**
 * ����������
 * @author glen
 *
 */
public class CreateTree {

	// ��֪ǰ������� �������
	// ǰ�������˳����: CABGHEDF
	// ���������˳����: GBHACDEF
	public static Node preAndMid(String preStr, String midStr) {
		if (preStr == null || preStr.length() == 0 || midStr == null || midStr.length() == 0)
			return null;
		// ��ȡ���ڵ�
		char rootElement = preStr.charAt(0);
		Node root = new Node(String.valueOf(rootElement), null, null);
		if (midStr.length() == 1) //��ʾֻ��һ�����ڵ�
			return root;
		// ��ȡ���ڵ��ֵ�������������е�λ��
		int index = midStr.indexOf(rootElement);

		String leftChildStr = midStr.substring(0, index);
		if (leftChildStr.isEmpty()) { // ��ʾû��������
			root.left = null;
		} else {
			root.left = preAndMid(preStr.substring(1, index + 1), leftChildStr);
		}

		String rightChildStr = midStr.substring(index + 1);
		if (rightChildStr.isEmpty()) { // ��ʾû��������
			root.right = null;
		} else {
			root.right = preAndMid(preStr.substring(index + 1), rightChildStr);
		}
		return root;
	}

	// ��֪��������� ���ǰ��
	// ���������˳����: GHBADFEC
	// ���������˳����: GBHACDEF
	public static Node postAndMid(String postStr, String midStr) {
		if (postStr == null || postStr.length() == 0 || midStr == null || midStr.length() == 0)
			return null;
		// ��ȡ���ڵ�
		char rootElement = postStr.charAt(postStr.length()-1);
		Node root = new Node(String.valueOf(rootElement), null, null);
		if (midStr.length() == 1)
			return root;
		// ��ȡ���ڵ��ֵ�������������е�λ��
		int index = midStr.indexOf(rootElement);

		String leftChildStr = midStr.substring(0, index);
		if (leftChildStr.isEmpty()) { // ��ʾû��������
			root.left = null;
		} else {
			root.left = postAndMid(postStr.substring(0, index), leftChildStr);
		}

		String rightChildStr = midStr.substring(index + 1);
		if (rightChildStr.isEmpty()) { // ��ʾû��������
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
