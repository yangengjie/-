package com.ygj.map;

import com.ygj.set.Set.Vistor;
import com.ygj.set.Person;
import com.ygj.set.TreeSet;

public class Main {

	public static void main(String[] args) {
		
		TreeSet<Person> set=new TreeSet<>();
		Person person1=new Person(110,"jack");
		Person person2=new Person(120,"jim");
		set.add(person1);
		set.add(person2);
		set.traversal(new Vistor<Person>() {
			
			@Override
			public boolean vistor(Person element) {
				System.out.println(element);
				return false;
			}
		});
	}

}
