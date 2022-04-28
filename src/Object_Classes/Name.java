package Object_Classes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Name implements Serializable{
private String firstname;
private String lastname;

public Name(String firstname, String lastname) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

@Override
public String toString() {
	return " FirstName: " + firstname + " LastName: " + lastname ;
}

}
