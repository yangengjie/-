package com.ygj;

import com.ygj.printer.BinaryTrees;
import com.ygj.tree.BST;
import com.ygj.tree.BinaryTree;
import com.ygj.tree.BinaryTree.Visitor;

public class Main {

	public static void main(String[] args) {
		int[] array = { 7, 5, 48, 44, 26, 63, 39, 96, 29, 37, 100 };
		BST<Integer> tree = new BST<>();
		for (int i = 0; i < array.length; i++) {
			tree.add(array[i]);
		}
		preOrder(tree);
		System.out.println("");
		inOrder(tree);
		System.out.println("");
		postOrder(tree);
		System.out.println("");
		BinaryTrees.print(tree);
	}

	static void preOrder(BST<Integer> tree) {
		tree.preorder(new Visitor<Integer>() {

			@Override
			public boolean visit(Integer e) {
				System.out.print(e + "_");
				return false;
			}

		});
	}

	static void inOrder(BST<Integer> tree) {
		tree.inorder(new Visitor<Integer>() {

			@Override
			public boolean visit(Integer e) {
				System.out.print(e + "_");
				return false;
			}

		});
	}

	static void postOrder(BST<Integer> tree) {
		tree.postorder(new Visitor<Integer>() {

			@Override
			public boolean visit(Integer e) {
				System.out.print(e + "_");
				return false;
			}

		});
	}

}
