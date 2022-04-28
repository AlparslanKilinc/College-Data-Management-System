package Panels_Demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PaneAssembler {
	private VBox root;
	private PaneswitchPane switchPane;
	private InstructorPanel instructorPane;
	private StudentPanel studentPane;
	private TextBookPanel textbookPane;
	private VBox localRoot;
	
	
	public PaneAssembler(Stage primaryStage) {
		
		root = new VBox(40);
		root.setAlignment(Pos.CENTER);
		switchPane = new PaneswitchPane();
		Button StudentBtn = switchPane.getStudentPaneBtn();
		Button InstructorBtn = switchPane.getInstructorPaneBtn();
		Button textbookBtn = switchPane.getTextbookPaneBtn();
		
	
		localRoot = new VBox();
		localRoot.setAlignment(Pos.CENTER);
		instructorPane = new InstructorPanel();
		textbookPane = new TextBookPanel();
		studentPane = new StudentPanel();
		
		
	
	
		
		StudentBtn.setOnAction(e -> {

			
			root.getChildren().removeAll(switchPane.getSwitchPane(), localRoot);
			
			localRoot = studentPane.getRoot();
			root.getChildren().addAll(switchPane.getSwitchPane(), localRoot);
			primaryStage.setTitle("Student Options");
		});
		
		InstructorBtn.setOnAction(e -> {

			
			root.getChildren().removeAll(switchPane.getSwitchPane(), localRoot);
			
			localRoot = instructorPane.getRoot();
			root.getChildren().addAll(switchPane.getSwitchPane(), localRoot);
			primaryStage.setTitle("Instructor Options");
		});
		
		textbookBtn.setOnAction(e -> {
			root.getChildren().remove(switchPane.getSwitchPane());
			root.getChildren().removeAll(localRoot);
			
			localRoot = textbookPane.getRoot();
			root.getChildren().addAll(switchPane.getSwitchPane(), localRoot);
			primaryStage.setTitle("TextBook Options");
			
		});
		
		
		
		root.getChildren().addAll(switchPane.getSwitchPane(), localRoot);
		Scene scene = new Scene(root, 800, 950);
		primaryStage.setTitle("College Data Book");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
	

}