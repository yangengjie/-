package com.ygj;

import com.ygj.printer.BinaryTrees;
import com.ygj.printer.Printer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree<Integer> avl=new AVLTree<Integer>();
		int[] array= {13,14,15,12,11,17,16,8,9,1};
		for (int i=0;i<array.length;i++) {
//			System.out.println("["+array[i]+"]");
			avl.add(array[i]);
			
//			System.out.println("----------------------------------------------");
		}
		BinaryTrees.println(avl);
		for (int i=0;i<array.length;i++) {
			System.out.println("["+array[i]+"]");
			avl.remove(array[i]);
			BinaryTrees.println(avl);
			System.out.println("----------------------------------------------");
		}
	}

}
