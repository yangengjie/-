package com.ygj;

public class Student implements Comparable<Student> {

	private int age;
	private int score;

	public Student(int age, int score) {
		this.age = age;
		this.score = score;
	}

	@Override
	public int compareTo(Student o) {
		return age - o.age;
	}

}
