package com.ygj.map;

import com.ygj.map.Map.Visitor;
import com.ygj.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		map.put(1, "a");
		map.put(3, "b");
		map.put(3, "e");
		map.put(2, "c");
		map.put(2, "f");
		map.put(6, "d");

		
		map.remove(1);
		map.remove(3);
		map.remove(2);
		
		map.traversal(new Visitor<Integer, String>() {

			@Override
			public boolean visitor(Integer key, String value, boolean color) {
				System.out.println((color == true ? "R" : "B") + "_" + key + "_" + value);
				return false;
			}
		});
	}
}
