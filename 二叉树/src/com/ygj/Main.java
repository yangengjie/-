package com.ygj;

import com.ygj.BinarySearchTree.Visitor;
import com.ygj.printer.BinaryTrees;
import com.ygj.printer.Printer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<Person> bst = new BinarySearchTree<Person>();
		int[] array = { 7, 4, 9, 2, 5, 8, 11, 1, 3, 10, 12 };
		String[] nameArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" };
		for (int i = 0; i < array.length; i++) {
			bst.add(new Person(array[i], nameArray[i]));
		}
		BinaryTrees.println(bst);
		bst.levelorder(new Visitor<Person>() {

			@Override
			public boolean visit(Person e) {
				System.out.println(e);
				return e.getAge() == 9;
			}
		});
	}

}
