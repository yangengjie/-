package com.ygj.map.model;

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
		hash = 31 * hash + name == null ? 0 : name.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || this.getClass() != obj.getClass())
			return false;
		Person person = (Person) obj;
		return this.age == person.age && 
				this.height == person.height &&
				this.name == null ? person.name == null : this.name.equals(person.name);
	}
}
