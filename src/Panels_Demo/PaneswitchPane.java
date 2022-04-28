package Panels_Demo;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PaneswitchPane {
	private Button StudentPaneBtn;
	private Button textbookPaneBtn;
	private Button InstructorPaneBtn;
	
	private HBox switchPane;

	public PaneswitchPane() {
		StudentPaneBtn = new Button("STUDENT OPTIONS");
		InstructorPaneBtn = new Button("INSTRUCTOR OPTIONS");
		textbookPaneBtn = new Button("TEXTBOOK OPTIONS");
		
		switchPane = new HBox(40);
		switchPane.setAlignment(Pos.CENTER);
		switchPane.getChildren().addAll(InstructorPaneBtn, StudentPaneBtn, textbookPaneBtn);
	}

	

	public Button getStudentPaneBtn() {
		return StudentPaneBtn;
	}

	public void setStudentPaneBtn(Button studentPaneBtn) {
		StudentPaneBtn = studentPaneBtn;
	}

	public Button getTextbookPaneBtn() {
		return textbookPaneBtn;
	}

	public void setTextbookPaneBtn(Button textbookPaneBtn) {
		this.textbookPaneBtn = textbookPaneBtn;
	}

	public Button getInstructorPaneBtn() {
		return InstructorPaneBtn;
	}

	public void setInstructorPaneBtn(Button instructorPaneBtn) {
		InstructorPaneBtn = instructorPaneBtn;
	}

	public HBox getSwitchPane() {
		return switchPane;
	}

	public void setSwitchPane(HBox switchPane) {
		this.switchPane = switchPane;
	}

}

