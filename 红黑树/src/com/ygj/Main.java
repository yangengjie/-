package com.ygj;

import com.ygj.printer.BinaryTrees;
import com.ygj.printer.Printer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RBTree<Integer> rbTree=new RBTree<Integer>();
		int[] array= {13,14,15,12,11,17,16,8,9,1};
		for (int i=0;i<array.length;i++) {
//			System.out.println("["+array[i]+"]");
			rbTree.add(array[i]);
//			BinaryTrees.println(rbTree);
//			System.out.println("----------------------------------------------");
		}
		BinaryTrees.println(rbTree);
		
		for (int i=0;i<array.length;i++) {
			System.out.println("["+array[i]+"]");
			rbTree.remove(array[i]);
			BinaryTrees.println(rbTree);
			System.out.println("----------------------------------------------");
		}
	}

}
