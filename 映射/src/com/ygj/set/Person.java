package com.ygj.set;

public class Person implements Comparable<Person> {
	private int age;
	private String name;

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	@Override
	public String toString() {
		return "age is " + age + ", name is " + name;
	}

	@Override
	public int compareTo(Person o) {
		return this.age - o.age;
	}
}
