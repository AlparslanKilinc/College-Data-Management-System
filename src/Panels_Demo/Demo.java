package Panels_Demo;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Object_Classes.Instructor;
import Object_Classes.Student;
import Object_Classes.Textbook;
import Utilities.PersonBag;
import Utilities.TextbookBag;
import Utilities.utilities;

public class Demo extends Application implements Initializable{



	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	File file = new File("Data/Persons.dat");
	File file2= new File("Data/Textbooks.dat");
	 HBox labelbox = new HBox();
	 VBox root = new VBox();
	Button Import = new Button("Start");
	Label label = new Label();
	
	label.setMinSize(50, 50);
	label.setFont(Font.font ("Verdana", 20));
	label.setText("College Data Book");
	
	Import.setPrefSize(70, 30);
	Import.setMinWidth(175);
	Import.setOnAction( t ->{
		if(!file.exists()||!file2.exists()) {
			System.out.println("Hi");
			Import();
			
		}
		new PaneAssembler(primaryStage);
	});
	labelbox.getChildren().add(label);
	labelbox.setAlignment(Pos.CENTER);
	root.getChildren().addAll(labelbox,Import);
	root.setAlignment(Pos.CENTER);
	
	Scene scene = new Scene(root,800,950);
	
	primaryStage.setScene(scene);
	primaryStage.setTitle("MAIN MENU");
	primaryStage.show();
	
	
		
		
		
		
	
		
		
	}
	private static void Import() {
		Textbook[] bookArr = utilities.importBooks();
		Instructor[] instructorArr = utilities.importInstructors();
		Student[] studentArr = utilities.importStudents();
		PersonBag pbag = utilities.loadPerson(studentArr, instructorArr);
		TextbookBag tbag = utilities.loadTextbook(bookArr);
		utilities.savePerson(pbag);
		utilities.saveTextbook(tbag);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	}
}
