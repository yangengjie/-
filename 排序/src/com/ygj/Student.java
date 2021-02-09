package com.ygj;

public class Student implements Comparable<Student> {

	public int age;
	public int score;

	public Student(int score, int age) {
		this.age = age;
		this.score = score;
	}

	@Override
	public int compareTo(Student o) {
		return age - o.age;
	}

}
