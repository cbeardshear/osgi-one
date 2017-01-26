package com.cb.osgi.simple.rest.person;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PersonServiceImpl implements PersonService {
	
	private final Map<Integer, Person> personMap = new HashMap<>();
	
	public PersonServiceImpl() {
		personMap.put(new Integer(1001),new Person(1001,"John Doe","Admin"));
		personMap.put(new Integer(1002),new Person(1002,"Lanny McDonald","Operator"));
		personMap.put(new Integer(1003),new Person(1003,"Hardy Astrom","Client"));
	}

	@Override
	public Collection<Person> getAll() {
		return personMap.values();
		//return (Person[])personMap.values().toArray(new Person[personMap.size()]);
	}

	@Override
	public Person getPerson(String id) {
		return personMap.get(Integer.parseInt(id));
	}

	@Override
	public void updatePerson(String id, Person person) {
		personMap.put(Integer.parseInt(id), person);
	}

	@Override
	public void addPerson(Person person) {
		personMap.put(new Integer(person.getId()), person);
		
	}

}
