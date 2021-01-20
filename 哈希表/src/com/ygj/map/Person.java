package com.ygj.map;

public class Person {
	private String name;
	private float height;
	private int age;

	public Person(String name, float height, int age) {
		this.name = name;
		this.height = height;
		this.age = age;
	}

	@Override
	public int hashCode() {
		int hash = Integer.hashCode(age);
		hash = 31 * hash + Float.hashCode(height);
		hash = 31 * hash + name.hashCode();
		return hash;
	}
}
