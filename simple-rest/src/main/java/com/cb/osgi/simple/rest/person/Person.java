package com.cb.osgi.simple.rest.person;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

	private int id;
	private String name;
	private String role;
	
	public Person(){}

	public Person(int id, String name, String role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
