package Utilities;

import java.io.Serializable;

import Object_Classes.Person;

public class PersonBag implements Serializable {

	private Person[] personArr;
	private int nElms;

	public PersonBag(int Maxsize) {
		personArr = new Person[Maxsize];

		nElms = 0;
	}

	public void insert(Person person) {
		
			try {
				personArr[nElms++] = person;
			} catch (ArrayIndexOutOfBoundsException e) {
				Person[] arr= new Person[personArr.length+1];
				for(int i=0; i<personArr.length; i++) {
					arr[i]=personArr[i];
				}
				arr[personArr.length]=person;
				setPersonArr(arr);
			}
		
		
	}

	public void display() {
		for (int i = 0; i < nElms; i++) {

			System.out.println(personArr[i]);

		}
		System.out.println();
	}

	public Person searchById(String id) {

		for (int i = 0; i < nElms; i++) {
			if (personArr[i].getId().equals(id)) {
				return personArr[i];
			}

		}
		return null;

	}

	public Person deleteById(String id) {
		int i;
		for (i = 0; i < nElms; i++) {
			if (personArr[i].getId().equals(id)) {
				break;
			}
		}
		if (i == nElms) {
			return null;

		} else {
			Person temperory = personArr[i];
			for (int j = i; j < nElms - 1; j++) {
				personArr[j] = personArr[j+1];
			}
			nElms--;
			return temperory;
		}
	}

	public Person[] getPersonArr() {
		return personArr;
	}

	public void setPersonArr(Person[] personArr) {
		this.personArr = personArr;
	}

}
