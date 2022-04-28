package Object_Classes;

import java.io.Serializable;

public abstract class Person implements Serializable {
	
	private Name name;
	public String id;
	private static int idCounter;
	
	public Person(Name name) {
		this.name = name;
		this.id = String.valueOf(idCounter++);
	}
	

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public static void setIdCounter(int idCounter) {
		Person.idCounter = idCounter;
	}


	public int getIdCounter() {
		return idCounter;
	}

	@Override
	public String toString() {
		return   name + " ID: " + id ;
	}



}
