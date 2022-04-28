package Utilities;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.util.Scanner;

import Object_Classes.Instructor;
import Object_Classes.Name;
import Object_Classes.Student;
import Object_Classes.Textbook;

public class utilities  {
	
	////// write files
	public static void writerank(String outputFilename) {
		String[]rank= {"Instructor","Proffesor","Assistant Professor","Associate Professor"};
		try {
			FileWriter fw= new FileWriter(outputFilename);
			PrintWriter pw = new PrintWriter(fw);
			for(int i=0; i<rank.length;i++) {
				pw.print(rank[i]+"\n");
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writemajors( String outputFilename) {
	String[] MA = {"ACC", "BUS", "MKT", "RET", "AUT", "CYB", "COT", "DRF", "ELT", "ENS",
				"FPT", "TYT", "CHI", "CIN", "COM", "DNC", "DMA", "ENG", "FRE", "GER", "GRD", "HUM", "INT", "ITL", "JPN", "LAT", "MUS" , 
				"MTR", "PHL", "ART", "SPN", "THR", "WST", "HIS", "SOC", "ASL", "CDC", "DTE", "PAR", "PFS", "HSC", "MED", "HIT", "HUS" , 
				"NUR", "OTA", "PED", "PTA", "PNU", "AST", "BIO", "CHE", "ESC", "ENV", "MAR", "MAT", "MET", "PHY", "ANT", "ECO" ,
				"GEO", "POL", "PSY", "COL", "CSE", "CRJ", "CUL", "EDU", "ESL", "HVA", "HRM", "CST", "IND", "LAW", "LIB", "MFT" ,"POA",
				"RTV", "RDG", "VST"};
		try {
			FileWriter fw= new FileWriter(outputFilename);
			PrintWriter pw = new PrintWriter(fw);
			for(int i=0; i<MA.length;i++) {
				pw.print(MA[i]+"\n");
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

	/////// emit objects
	public static Name emitName(String firstNameFileName, String lastNameFileName) {
		int i=0;
		int j=0;
		File firstname = new File(firstNameFileName);
		File lastname = new File(lastNameFileName);
		Name name = null;
		String[] firstnamesarr= new String[20000];
		String[] lastnamesarr= new String[20000];
		
		try {
			Scanner firstnamescanner = new Scanner(firstname);
			Scanner lastnamescanner = new Scanner(lastname);
			
			while(firstnamescanner.hasNextLine()) {
				firstnamesarr[i++] = firstnamescanner.nextLine();
				
			}
			while(lastnamescanner.hasNextLine()) {
				lastnamesarr[j++] = lastnamescanner.nextLine();
			}
			int randomNumber=(int) (Math.random()*2000);
			name = new Name(firstnamesarr[randomNumber],lastnamesarr[randomNumber]);
			
			firstnamescanner.close();
			lastnamescanner.close();
		}
		
		catch (FileNotFoundException e) {
			System.out.println("Can not find the file");
		}
		
		return name;
	}
	
	
	public static String[][] emitTitleAndIsbn(String titleFileName, String isbnFileName) {
		File titlefile = new File(titleFileName);
		File isbnfile = new File(isbnFileName);
		String[][] titleandisbn = new String[10000][2];
		String titles;
		String isbns;
		int i =0;
		int j=0;
		try {
			Scanner title = new Scanner(titlefile);
			Scanner isbn = new Scanner(isbnfile);
			
			while(title.hasNextLine()) {
				 titles=title.nextLine();
				titleandisbn[i++][0]= titles;
			}
			while(isbn.hasNextLine()) {
				 isbns=isbn.nextLine();
				 titleandisbn[j++][1]= isbns;
			}
			title.close();
			isbn.close();
			
		} catch (FileNotFoundException e) {
			
		}
		
		
		return titleandisbn;
		
	}
	
	public static double emitPrice() {
		double randomprice=Math.round(Math.random()*199.99*100);
		double price=randomprice/100;
		return price;
		
	}

	public static Textbook[] importBooks() {
		
		int i=0;
		Textbook[] textbook = new Textbook[10000];

	
		for(i=0; i<textbook.length; i++) {
		Name name = utilities.emitName("Files/FirstNames.txt", "Files/LastNames.txt");
		double price = utilities.emitPrice();
		String[][] titleandisbn= utilities.emitTitleAndIsbn("Files/textbook_titles.txt", 
				"Files/textbook_isbns.txt");
		textbook[i]= new Textbook(name,titleandisbn[i][0],titleandisbn[i][1],price);
			
		}
	
				return textbook;
	}
	
	
	public static Student[] importStudents() {
		Student[] students =new Student[10000];
		String [] MA= new String[80];
		int i =0;
		File majors= new File("Files/majors.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(majors);
			while(scanner.hasNextLine()) {
				 MA[i++] = scanner.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(i=0; i<students.length; i++) {
		Name name = utilities.emitName("Files/FirstNames.txt", "Files/LastNames.txt");
		double randomgpa=Math.round((Math.random()*4*10));
		double gpa=randomgpa/10;
		int randomNumberForMajor=(int) (Math.random()*MA.length);
		String majorss = MA[randomNumberForMajor];
		students[i]=new Student(name,gpa,majorss);
		}
		return students;
		
	}
	
	
	public static Instructor[] importInstructors() {
		Instructor[] instructor = new Instructor[500];
		String [] rank=new String [4];
		int i=0;
		/// rank 
		File ranks = new File("Files/ranks.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(ranks);
			while(scanner.hasNextLine()) {
				 rank[i++] = scanner.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(i=0; i<instructor.length; i++) {
		Name name = utilities.emitName("Files/FirstNames.txt", "Files/LastNames.txt");
		//// Salary
		double randomsalary=10000.00+(double)(Math.random()*100000.00+1);
		String salarys= String.format("%.2f", randomsalary);
		double salary =Double.parseDouble(salarys);
		int randomrank=(int) (Math.random()*rank.length);
		String ranksy = rank[randomrank];
		///// rank
		
		instructor[i]=new Instructor(name,ranksy,salary);
		}
		
		return instructor;
	}
	
	////////// load 
	public static PersonBag loadPerson(Student[] studentArr, Instructor[] instructorArr) {
		
		PersonBag pbag = new PersonBag(1500);
		for(int i=0; i<studentArr.length; i++) {
		pbag.insert(studentArr[i]);
		}
		for(int i=0; i<instructorArr.length; i++) {
			pbag.insert(instructorArr[i]);
			}
		
		return pbag;
	}
	
	
	public static TextbookBag loadTextbook(Textbook[] bookArr) {
		TextbookBag tbag = new TextbookBag(10000);
		for(int i=0; i<bookArr.length; i++) {
		tbag.insert(bookArr[i]);
		}
		return tbag;
	}
	

	
	////////// save 
	public static void saveTextbook(TextbookBag textbook) {
		
		try {
			FileOutputStream fos = new FileOutputStream("Data/Textbooks.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(textbook);
			oos.close();
		} catch (FileNotFoundException e) {
			
			
		} catch (IOException e) {
			
			
		}
		
	}

	
	public static void savePerson(PersonBag person) {
		
		try {
			File file = new File("Data/Persons.dat");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(person);
			oos.close();
		} catch (FileNotFoundException e) {
			
			
		} catch (IOException e) {
			
			
		}
		
	}

		
		
	
	/////// restore 
	public static TextbookBag restorebook() {
		TextbookBag  books= null;
		try {
			FileInputStream fis = new FileInputStream("Data/Textbooks.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
		  books = (TextbookBag) ois.readObject();
			ois.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}


	public static PersonBag restoreperson() {
		
		PersonBag persons=null;
		try {
			FileInputStream fis = new FileInputStream("Data/Persons.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			 persons =  (PersonBag) ois.readObject();
			ois.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return persons;
	}
	
	
}
