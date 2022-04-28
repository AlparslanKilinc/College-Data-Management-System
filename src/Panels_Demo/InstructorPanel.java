package Panels_Demo;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Object_Classes.Instructor;
import Object_Classes.Name;
import Object_Classes.Person;
import Utilities.PersonBag;
import Utilities.utilities;
import Exceptions_Alerts.EnterException;
import Exceptions_Alerts.NameException;
import Exceptions_Alerts.Popbox;
import Exceptions_Alerts.RangeException;
import Exceptions_Alerts.RankException;
import Exceptions_Alerts.SalaryException;


public class InstructorPanel {

	PersonBag personbag=utilities.restoreperson();
	Person[] personarr=personbag.getPersonArr();
	int ids = personarr.length-1;
	
	
	private VBox root;
	private HBox fieldBox;
	private HBox textBox;
	private HBox buttonBox;
	private HBox emptyBox;
	private VBox idfields;
	private TextField firstname;
	private TextField lastname;
	private TextField salary;
	private TextField rank;
	private TextField id;
	private TextArea area;
	private Button Search;
	private Button Remove;
	private Button Exit;
	private Button Insert;
	private Button Update;
	

	public InstructorPanel() {
		
		
		idfields = new VBox(4);
		root = new VBox(30);
		fieldBox = new HBox(5);
		textBox = new HBox(20);
		buttonBox = new HBox(20);
		emptyBox = new HBox(20);

		firstname = new TextField();
		firstname.setPromptText("FIRST NAME");

		lastname = new TextField();
		lastname.setPromptText("LAST NAME");

		rank = new TextField();
		rank.setPromptText("RANK");

		salary = new TextField();
		salary.setPromptText("SALARY");

		id = new TextField();
		id.setPromptText("ID");


		area = new TextArea();
		area.setPrefHeight(25);
		area.setMinWidth(600);
		
		
		
		/// Exit
		

		Exit = new Button("EXIT");
		Exit.setPrefSize(70, 30);
		Exit.setOnAction(e -> {
			utilities.savePerson(personbag);
			Platform.exit();
			
		});

		////// Search
		Search = new Button("SEARCH");
                Search.setPrefSize(70,30);
		Search.setOnAction(x -> {
			area.clear();

			try {
				String searchids = id.getText();
				Person found = personbag.searchById(searchids);
				if (searchids.isEmpty()) {
					String title3 = "ERROR";
					String message3 = "SEARCH ID IS NOT ENTERED";
					String title4 = "ENTER A INSTRUCTOR ID";
					Popbox.errorBox(message3, title3,title4);
				}
			
				if (found instanceof Instructor) {
					area.appendText(found.toString());
				}
				if (!(found instanceof Instructor)&& !searchids.isEmpty()) {
					String title3 = "WARNING";
					String title4 = "ENTER A INSTRUCTOR ID";
					String message3 = "THE ID ENTERED  IS NOT OF A INSTRUCTOR \n"+"ID ENTERED: "+searchids;
					Popbox.warningBox(message3, title3,title4);
				}

			} catch (NullPointerException e1) {

			}
		});

		/// Remove
		Remove = new Button("REMOVE");
		Remove.setPrefSize(70, 30);
		Remove.setOnAction(f -> {
			area.clear();
			try {
				String removeids = id.getText();
				if (removeids.isEmpty()) {
					String title3 = "ERROR";
					String title4 = "ENTER A INSTRUCTOR ID TO REMOVE";
					String message3 = "REMOVE ID FIELD IS EMPTY";
					Popbox.errorBox(message3, title3,title4);
				}
				if (!removeids.matches("[0-9]+")&&!removeids.isEmpty()) {
					String title3 = "WARNING";
					String title4 = "ENTER A INSTRUCTOR ID ";
					String message3 = "ID SHOULD BE OF A INSTRUCTOR ";
					Popbox.warningBox(message3, title3,title4);
				}
				Person found = personbag.searchById(removeids);

				if (found instanceof Instructor) {
					personbag.deleteById(removeids);
					String title3 = "INFORMATION";
					String title4 = "SUCCSESS";
					String message3 = "STUDENT WITH ID: "+removeids+" IS REMOVED";
					Popbox.infoBox(message3, title3,title4);
				}if(!(found instanceof Instructor)&& !removeids.isEmpty()){
					String title3 = "ERROR";
					String title4 = "REMOVE FAILED";
					String message3 = "THE ID ENTERED  IS NOT OF A INSTRUCTOR \n"+"ID ENTERED: "+removeids;
					Popbox.errorBox(message3, title3,title4);
				}

			} catch (NullPointerException e1) {

			}
			

		});

		//////// Insert

		Insert = new Button("INSERT");
		Insert.setPrefSize(70, 30);
		Insert.setOnAction(x -> {
			area.clear();

			try {

				String fname = firstname.getText();
				String lname = lastname.getText();
				String salaryz = salary.getText();
				String ranks = rank.getText();

				if (!fname.matches("[a-zA-Z]+") && !fname.isEmpty()
						|| !lname.matches(("[a-zA-Z]+")) && !lname.isEmpty()) {
					throw new NameException();
				}
				if (fname.isEmpty() || lname.isEmpty()) {
					throw new EnterException();
				}
				Name name = new Name(fname, lname);
				
				if (salaryz.isEmpty()) {
					throw new EnterException();
				}
				if (!salaryz.matches("^[0-9.;]+$") && !salaryz.isEmpty()) {
					throw new SalaryException();
				}
				
				double salaryt = Double.parseDouble(salaryz);
				String sal = String.format("%.2f", salaryt);
				double salary = Double.parseDouble(sal);
				if (salary <= 10000 || salary >=100000 && !salaryz.isEmpty()) {
					throw new RangeException();
				}
				if (ranks.isEmpty()) {
					throw new EnterException();
				}
				if (!ranks.matches("^[ A-Za-z]+$") && !ranks.isEmpty()) {
					throw new RankException();
				}
				

				Person instructor = new Instructor(name, ranks, salary);
				do {
					ids++;
					if(ids%2==0){
					instructor.setId(String.valueOf(ids));
					personbag.insert(instructor);
					area.appendText(instructor.toString());
					}
				
				}while(instructor.getId().matches(String.valueOf(ids)));
				
			} catch (NumberFormatException e1) {

			} catch (RangeException e2) {
				
				String title3 = "WARNING";
				String title4 = "SALARY MUST BE BETWEEN 10,000 AND 100,000";
				String message3 = "ENTER THE SALARY AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (NameException e1) {
				String title3 = "WARNING";
				String title4 = "NAME SHOULD ONLY CONTAIN LETTERS";
				String message3 = "ENTER THE NAME AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (RankException e1) {
				String title3 = "WARNING";
				String title4 = "RANK SHOUDLD ONLY CONTAIN LETTERS ";
				String message3 = "ENTER THE RANK AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (SalaryException e1) {
				String title3 = "WARNING";
				String title4 = "SALARY SHOULD BE ONLY POSITIVE NUMBERS ";
				String message3 = "ENTER THE SALARY AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (EnterException e1) {
				String title3 = "ERROR";
				String title4 = "ALL FIELDS MUST BE ENTERED";
				String message3 = "FIELDS WERE LEFT EMPTY ";
				Popbox.errorBox(message3, title3,title4);
			}
			
		});

		////// UPDATE
		Update = new Button("UPDATE");
		Update.setPrefSize(70, 30);
		Update.setOnAction(e -> {
			area.clear();
			String idfound = id.getText();
			Person found = personbag.searchById(idfound);
			String ranks = rank.getText();
			String salarys = salary.getText();
			if (found instanceof Instructor) {
				try {
					String fname = firstname.getText();
					String lname = lastname.getText();
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

					if (!ranks.matches("^[ A-Za-z]+$") && !ranks.isEmpty()) {
						throw new RankException();
					}

					if (ranks.matches("^[ A-Za-z]+$")) {
						((Instructor) found).setRank(ranks);
					}
					double salarysd = Double.parseDouble(salarys);
					if (salarysd >= 100000 || salarysd <= 10000) {
						throw new RangeException();
					}
					if (!salarys.matches("^[0-9.;]+$") && !salarys.isEmpty()) {
						throw new SalaryException();
					}

					if (salarys.matches("^[0-9.;]+$") && salarysd <= 100000 && salarysd >= 10000) {
						((Instructor) found).setSalary(salarysd);
					}

				} catch (NumberFormatException e1) {

				} catch (RangeException e2) {
					String title3 = "WARNING";
					String title4 = "SALARY MUST BE BETWEEN 10,000 AND 100,000";
					String message3 = "ENTER THE SALARY AGAIN ";
					Popbox.warningBox(message3, title3,title4);
				} catch (NameException e1) {
					String title3 = "WARNING";
					String title4 = "NAME SHOULD ONLY HAVE LETTERS";
					String message3 = "ENTER THE NAME AGAIN ";
					Popbox.warningBox(message3, title3,title4);

				} catch (RankException e1) {
					String title3 = "WARNING";
					String title4 = "RANK SHOUDLD ONLY CONTAIN LETTERS";
					String message3 = "ENTER THE RANK AGAIN ";
					Popbox.warningBox(message3, title3,title4);
				} catch (SalaryException e1) {
					String title3 = "WARNING";
					String title4 = "SALARY SHOULD BE ONLY POSITIVE NUMBERS";
					String message3 = "ENTER THE SALARY AGAIN ";
					Popbox.warningBox(message3, title3,title4);
					area.appendText(found.toString());
				} 
			}else {
					if (idfound.isEmpty()) {
						String title3 = "EROR";
						String title4 = "ENTER A INSTRUCTOR ID TO UPDATE";
						String message3 = "UPDATE ID FIELD IS EMPTY";
						Popbox.errorBox(message3, title3,title4);
					}
					if (!idfound.isEmpty()) {
						String title3 = "WARNING";
						String title4 = "ENTER A INSTRUCTOR ID TO UPDATE";
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
		fieldBox.getChildren().addAll(firstname, lastname, rank, salary, idfields);
		buttonBox.getChildren().addAll(Search, Remove, Exit, Insert, Update);
		textBox.getChildren().add(area);
		root.getChildren().addAll(emptyBox, fieldBox, buttonBox, textBox);
		setAlignments();
		
	}

	public TextField getFirstname() {
		return firstname;
	}

	public void setFirstname(TextField firstname) {
		this.firstname = firstname;
	}

	public TextField getLastname() {
		return lastname;
	}

	public void setLastname(TextField lastname) {
		this.lastname = lastname;
	}

	public TextField getSalary() {
		return salary;
	}

	public void setSalary(TextField salary) {
		this.salary = salary;
	}

	public TextField getRank() {
		return rank;
	}

	public void setRank(TextField rank) {
		this.rank = rank;
	}

	public VBox getRoot() {
		return root;
	}
	

	public PersonBag getPersonbag() {
		return personbag;
	}

	public void setPersonbag(PersonBag personbag) {
		this.personbag = personbag;
	}

	private void setAlignments() {
		root.setAlignment(Pos.CENTER);
		fieldBox.setAlignment(Pos.CENTER);
		textBox.setAlignment(Pos.CENTER);
		emptyBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
	}

}
