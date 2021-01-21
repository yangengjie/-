package com.ygj.map;

public class Main {

	public static void main(String[] args) {
		test1();
	}

	static void test1() {
		HashMap<Object, Integer> map = new HashMap<>();
		Person p1 = new Person("jack", 1.67f, 22);
		Person p2 = new Person("jack", 1.67f, 22);
		map.put(p1, 1);
		map.put(1, 2);
		map.put(p2, 3);
		map.put(null, 4);
//		System.out.println(map.size());
//		System.out.println(map.get(p2));
//		System.out.println(map.get(null));
		System.out.println(map.containsKey(p1));
		System.out.println(map.containsKey(null));
		System.out.println(map.containsValue(4));
	}

}
