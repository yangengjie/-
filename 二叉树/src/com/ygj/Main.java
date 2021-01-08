package com.ygj;

import java.util.Random;

import com.ygj.BinarySearchTree.Visitor;
import com.ygj.printer.BinaryTrees;
import com.ygj.printer.Printer;

public class Main {

	public static void main(String[] args) {
		test1();
	}
	
	
	static void test2() {
		BinarySearchTree<Integer> bst =new BinarySearchTree<Integer>();
		for (int i = 0; i < 40; i++) {
			bst.add(i+new Random().nextInt(100));
		}
		BinaryTrees.println(bst);
		System.out.println(bst.height(1));
		System.out.println(bst.isCompleteTree());
	}
	
	static void test1() {
		BinarySearchTree<Integer> bst =new BinarySearchTree<Integer>();
		int[] array= {8,4,12,3,7,9,15,2,6};
		for (int i : array) {
			bst.add(i);
		}
		BinaryTrees.println(bst);
		System.out.println(bst.height(6));
		System.out.println(bst.isCompleteTree());
		System.out.println(bst.successor(12));
	}
	static void test3() {
		BinarySearchTree<String> bst =new BinarySearchTree<String>();
		String[] array= {"C","A","E","B","D","F","G","H"};
		for (String i : array) {
			bst.add(i);
		}
		BinaryTrees.println(bst);
//		System.out.println(bst.height(6));
		System.out.println(bst.isCompleteTree());
	}

}
