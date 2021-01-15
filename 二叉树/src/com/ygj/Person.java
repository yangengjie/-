package com.ygj;

import java.awt.geom.AffineTransform;

public class Person implements Comparable<Person> {
	private int age;
	private String name;

	public Person(int age) {
		this.age = age;
	}
	
	public Person(int age,String name) {
		this.age = age;
		this.name=name;
	}

	public int getAge() {
		return age;
	}
	
	
	public void setAge(int age) {
		this.age=age;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Person person) {
		return this.age - person.age;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return age+"";
	}

}
