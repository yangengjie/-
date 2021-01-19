package com.ygj.set;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.ygj.set.Set.Vistor;

public class Main {

	public static void main(String[] args) {
//		test5();
//		Hashtable<Integer, String> hashtable=new Hashtable<Integer, String>();
//		hashtable.put(1, "a");
//		hashtable.put(2, "b");
//		hashtable.put(5, "d");
//		hashtable.put(4, "c");
//		for(Entry<Integer, String> entry : hashtable.entrySet()){
//			System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
//		}
		test4();
	}
	
	static void test5() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1, "a");
		map.put(2, "b");
		map.put(5, "d");
		map.put(4, "c");
		
		java.util.Set<Integer> set=map.keySet();
		for (Integer integer : set) {
			System.out.println(integer+"_"+map.get(integer));
		}
		
	}
	
	
	static void test4() {
		TreeMap<Integer, String> map=new TreeMap<Integer, String>();
		map.put(1, "a");
		map.put(3, "b");
		map.put(2, "c");
		
		java.util.Set<Integer> set=map.keySet();
		for (Integer integer : set) {
			System.out.println(integer+"_"+map.get(integer));
		}
	}
	
	
	static void test3() {
		LinkedHashSet< Integer> set=new LinkedHashSet<Integer>();
//		java.util.TreeSet<Integer> set=new java.util.TreeSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(4);
		set.add(3);
		
		Iterator<Integer> iterator=set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			
		}
		
		for (Integer integer : set) {
//			System.out.println(integer);
		}
		
	}
	
	
	static void test2() {
		Set<Integer> treeSet = new TreeSet<Integer>();
		treeSet.add(1);
		treeSet.add(3);
		treeSet.add(3);
		treeSet.add(8);
		treeSet.add(2);

		treeSet.traversal(new Vistor<Integer>() {

			@Override
			public boolean vistor(Integer element) {
				System.out.println(element);
				return false;
			}
		});
		System.out.println("---------------------");
		treeSet.remove(1);
		treeSet.remove(2);
		treeSet.remove(8);
		treeSet.traversal(new Vistor<Integer>() {

			@Override
			public boolean vistor(Integer element) {

				System.out.println(element);
				return false;
			}
		});
	}

	static void test1() {
		Set<Integer> set = new ListSet<Integer>();
		set.add(1);
		set.add(3);
		set.add(3);
		set.add(8);
		set.add(2);

		set.traversal(new Vistor<Integer>() {

			@Override
			public boolean vistor(Integer element) {
				System.out.println(element);
				return false;
			}
		});
		System.out.println("---------------------");
		set.remove(1);
		set.remove(2);
		set.remove(8);
		set.traversal(new Vistor<Integer>() {

			@Override
			public boolean vistor(Integer element) {

				System.out.println(element);
				return false;
			}
		});
	}

}
