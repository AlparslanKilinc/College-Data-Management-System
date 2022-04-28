package Panels_Demo;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Object_Classes.Name;
import Object_Classes.Person;
import Object_Classes.Student;
import Utilities.PersonBag;
import Utilities.utilities;
import Exceptions_Alerts.EnterException;
import Exceptions_Alerts.GPAException;
import Exceptions_Alerts.GPARangeException;
import Exceptions_Alerts.MajorException;
import Exceptions_Alerts.NameException;
import Exceptions_Alerts.Popbox;
import Exceptions_Alerts.RangeException;

public class StudentPanel {

	PersonBag personbag = utilities.restoreperson();
	Person[] personarr = personbag.getPersonArr();
	
	int ids = personarr.length-1;

	private VBox root;
	private HBox fieldBox;
	private HBox textBox;
	private HBox emptybox;
	private HBox buttonbox;
	private VBox idfields;
	private TextField firstname;
	private TextField lastname;
	private TextField gpa;
	private TextField major;
	private TextField id;
	private TextArea area;
	private Button Search;
	private Button Remove;
	private Button Exit;
	private Button Insert;
	private Button Update;
	

	public StudentPanel() {
		
		root = new VBox(30);
		buttonbox = new HBox(20);
		emptybox = new HBox(20);
		fieldBox = new HBox(5);
		textBox = new HBox(20);
		idfields = new VBox(4);

		firstname = new TextField();
		firstname.setPromptText("FIRST NAME");
		lastname = new TextField();
		lastname.setPromptText("LAST NAME");
		gpa = new TextField();
		gpa.setPromptText("GPA");
		major = new TextField();
		major.setPromptText("MAJOR");
		id = new TextField();
		id.setPromptText("ID");

		area = new TextArea();
		area.setPrefHeight(25);
		area.setMinWidth(600);

		///// BackUp
		Exit = new Button("EXIT");
		Exit.setPrefSize(70, 30);
		Exit.setOnAction(e -> {
			utilities.savePerson(personbag);
			Platform.exit();
			
		});
		///// Search
		Search = new Button("SEARCH");
		Search.setPrefSize(70, 30);
		Search.setOnAction(x -> {
			area.clear();

			try {
				String searchids = id.getText();
				Person found = personbag.searchById(searchids);

				if (searchids.isEmpty()) {
					String title3 = "ERROR";
					String message3 = "SEARCH ID IS NOT ENTERED";
					String title4 = "ENTER A STUDENT ID";
					Popbox.errorBox(message3, title3,title4);
				}

				if (found instanceof Student) {
					area.appendText(found.toString());
				} if(!(found instanceof Student)&& !searchids.isEmpty()) {
					String title3 = "WARNING";
					String title4 = "ENTER A STUDENT ID [ID's Start From 500]";
					String message3 = "THE ID ENTERED  IS NOT OF A STUDENT \n"+"ID ENTERED: "+searchids;
					Popbox.warningBox(message3, title3,title4);
				}

			} catch (NullPointerException e1) {

			}

		});

		////// Remove
		Remove = new Button("REMOVE");
		Remove.setPrefSize(70, 30);
		Remove.setOnAction(f -> {
			area.clear();

			try {
				String removeids = id.getText();
				if (removeids.isEmpty()) {
					String title3 = "ERROR";
					String title4 = "ENTER A STUDENT ID TO REMOVE [ID's Start From 500]";
					String message3 = "REMOVE ID FIELD IS EMPTY";
					Popbox.errorBox(message3, title3,title4);
				}
				if (!removeids.matches("[0-9]+")&&!removeids.isEmpty()) {
					String title3 = "WARNING";
					String title4 = "ENTER A STUDENT ID ";
					String message3 = "ID SHOULD BE OF A STUDENT ";
					Popbox.warningBox(message3, title3,title4);
				}
				Person found = personbag.searchById(removeids);

				if (found instanceof Student) {
					personbag.deleteById(removeids);
					String title3 = "INFORMATION";
					String title4 = "SUCCSESS";
					String message3 = "STUDENT WITH ID: "+removeids+" IS REMOVED";
					Popbox.infoBox(message3, title3,title4);
				} if(!(found instanceof Student)&& !removeids.isEmpty())  {
					String title3 = "ERROR";
					String title4 = "REMOVE FAILED";
					String message3 = "THE ID ENTERED  IS NOT OF A STUDENT \n"+"ID ENTERED: "+removeids;
					Popbox.errorBox(message3, title3,title4);
				}

			} catch (NullPointerException e1) {

			}
			
			

		});

		//// Insert

		Insert = new Button("INSERT");
		Insert.setPrefSize(70, 30);
		Insert.setOnAction(x -> {
			area.clear();

			try {
				String fname = firstname.getText();
				String lname = lastname.getText();
				String stringgpa = gpa.getText();
				String majors = major.getText();

				if (!fname.matches("[a-zA-Z]+") && !fname.isEmpty()
						|| !lname.matches(("[a-zA-Z]+")) && !lname.isEmpty()) {
					throw new NameException();
				}
				if (fname.isEmpty() || lname.isEmpty()) {
					throw new EnterException();
				}
				Name name = new Name(fname, lname);
				if (majors.length() > 3 || majors.length() < 3) {
					throw new RangeException();
				}
				if (!majors.matches("[a-zA-Z]+") && !majors.isEmpty()) {
					throw new MajorException();
				}
				if (majors.isEmpty()) {
					throw new EnterException();
				}
				double gpas = Double.parseDouble(stringgpa);
				String sal = String.format("%.1f", gpas);
				double gp = Double.parseDouble(sal);
				if (gp >= 4.0 || gp <= 0.0) {
					throw new GPARangeException();
				}
				if (!stringgpa.matches("^[0-9.;]+$") && !stringgpa.isEmpty()) {
					throw new GPAException();
				}
				if (stringgpa.isEmpty()) {
					throw new EnterException();
				}

				Person student = new Student(name, gp, majors);
				do {
					ids++;
					if(!(ids%2==0)){
					student.setId(String.valueOf(ids));
					personbag.insert(student);
					area.appendText(student.toString());
					}
				
				}while(student.getId().matches(String.valueOf(ids)));
				
			} catch (NumberFormatException e1) {

			} catch (GPARangeException e2) {
				
				String title3 = "WARNING";
				String title4 = "GPA MUST BE BETWEEN 0.0 AND 4.0";
				String message3 = "ENTER THE GPA AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (NameException e1) {
				String title3 = "WARNING";
				String title4 = "NAME SHOULD NOT CONTAIN NUMBERS";
				String message3 = "ENTER THE NAME AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (RangeException e1) {
				String title3 = "WARNING";
				String title4 = "MAJOR SHOULD BE 3 LETTERS LONG";
				String message3 = "ENTER THE MAJOR AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (MajorException e1) {
				String title3 = "WARNING";
				String title4 = "MAJOR SHOUDLD ONLY CONTAIN LETTERS:";
				String message3 = "ENTER THE MAJOR AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (GPAException e1) {
				String title3 = "WARNING";
				String title4 = "GPA SHOULD BE ONLY POSITIVE NUMBERS";
				String message3 = "ENTER THE GPA AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (EnterException e1) {
				String title3 = "ERROR";
				String title4 = "ALL FIELDS MUST BE ENTERED";
				String message3 = "FIELDS WERE LEFT EMPTY ";
				Popbox.errorBox(message3, title3,title4);
			}
		
		});
		/// Update
		Update = new Button("UPDATE");
		Update.setPrefSize(70, 30);
		Update.setOnAction(e -> {
			area.clear();

			String idfound = id.getText();
			Person found = personbag.searchById(idfound);
			if (found instanceof Student) {
				try {
					String fname = firstname.getText();
					String lname = lastname.getText();
					String gpazz = gpa.getText();
					String majors = major.getText();

					if (fname.matches("[a-zA-Z]+")) {
						Name n = found.getName();
						Name name1 = new Name(fname, n.getLastname());
						found.setName(name1);
					}
					if (lname.matches("[a-zA-Z]+")) {
						Name n = found.getName();
						Name name2 = new Name(n.getFirstname(), lname);
						found.setName(name2);
					}
					if (fname.matches("[a-zA-Z]+") && lname.matches(("[a-zA-Z]+"))) {
						Name finalname = new Name(fname, lname);
						found.setName(finalname);
					}
					if (!fname.matches("[a-zA-Z]+") && !fname.isEmpty()
							|| !lname.matches(("[a-zA-Z]+")) && !lname.isEmpty()) {
						throw new NameException();
					}

					if (!majors.matches("[a-zA-Z]+") && !majors.isEmpty()) {
						throw new MajorException();
					} else if (!(majors.length() == 3) && !majors.isEmpty()) {
						throw new RangeException();
					}
					if (majors.matches("[a-zA-Z]+") && majors.length() == 3) {
						String majorsup = majors.toUpperCase();
						((Student) found).setMajor(majorsup);
					}
					double gpar = Double.parseDouble(gpazz);
					if (gpar >= 4.0 || gpar <= 0) {
						throw new GPARangeException();
					}
					if (!gpazz.matches("^[0-9.;]+$") && !gpazz.isEmpty()) {
						throw new GPAException();
					}

					if (gpazz.matches("^[0-9.;]+$") && gpar <= 4.0 && gpar >= 0.0) {
						String sal = String.format("%.1f", gpar);
						double gp = Double.parseDouble(sal);
						((Student) found).setGpa(gp);
					}
				} catch (NumberFormatException e1) {

				} catch (GPARangeException e2) {
					String title3 = "WARNING";
					String title4 = "GPA MUST BE BETWEEN 0.0 AND 4.0";
					String message3 = "ENTER THE GPA AGAIN ";
					Popbox.warningBox(message3, title3,title4);
				} catch (NameException e1) {
					String title3 = "WARNING";
					String title4 = "NAME SHOULD NOT CONTAIN NUMBERS";
					String message3 = "ENTER THE NAME AGAIN ";
					Popbox.warningBox(message3, title3,title4);
				} catch (GPAException e1) {
					String title3 = "WARNING";
					String title4 = "GPA SHOULD BE ONLY POSITIVE NUMBERS:";
					String message3 = "ENTER THE GPA AGAIN ";
					Popbox.warningBox(message3, title3,title4);
				} catch (MajorException e1) {
					String title3 = "WARNING";
					String title4 = "MAJOR SHOULD ONLY CONTAIN LETTERS:";
					String message3 = "ENTER THE MAJOR AGAIN ";
					Popbox.warningBox(message3, title3,title4);
				} catch (RangeException e1) {
					String title3 = "WARNING";
					String title4 = "MAJOR SHOULD BE 3 LETTERS LONG";
					String message3 = "ENTER THE MAJOR AGAIN ";
					Popbox.warningBox(message3, title3,title4);
				}

			} else {
				if (idfound.isEmpty()) {
					String title3 = "EROR";
					String title4 = "ENTER A STUDENT ID TO UPDATE";
					String message3 = "UPDATE ID FIELD IS EMPTY";
					Popbox.errorBox(message3, title3,title4);
				}
				if (!idfound.isEmpty()) {
					String title3 = "WARNING";
					String title4 = "ENTER A STUDENT ID TO UPDATE";
					String message3 = "THIS ID DOES NOT CORRESPOND TO A STUDENT:"+idfound;
					Popbox.warningBox(message3, title3,title4);
				}
			}

			try {
				area.appendText(found.toString());
			} catch (Exception e1) {

			}

		});
		idfields.getChildren().addAll(id);
		fieldBox.getChildren().addAll(firstname, lastname, gpa, major, idfields);
		buttonbox.getChildren().addAll(Search, Remove, Exit, Insert, Update);
		textBox.getChildren().add(area);
		root.getChildren().addAll(emptybox, fieldBox, buttonbox, textBox);
		setAlignments();

	}

	public TextField getGpa() {
		return gpa;
	}

	public void setGpa(TextField gpa) {
		this.gpa = gpa;
	}

	public TextField getMajor() {
		return major;
	}

	public void setMajor(TextField major) {
		this.major = major;
	}

	public VBox getRoot() {
		return root;
	}

	private void setAlignments() {
		root.setAlignment(Pos.CENTER);
		textBox.setAlignment(Pos.CENTER);
		fieldBox.setAlignment(Pos.CENTER);
		buttonbox.setAlignment(Pos.CENTER);
		emptybox.setAlignment(Pos.CENTER);
	}

}
