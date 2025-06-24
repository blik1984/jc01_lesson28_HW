package com.edu.educational_system.model;

import java.util.Objects;

public abstract class Person {
	private String name;
	private String email;

	public Person(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public abstract void performRole();

	public abstract String getRoleDescription();

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(email, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name);
	}
}
