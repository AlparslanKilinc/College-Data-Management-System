package Exceptions_Alerts;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alertbox {

	public static void  display(String title, String message) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(400);
		Label l =new Label();
		l.setText(message);
		Button closeButton = new Button("Okay");
		closeButton.setOnAction(x ->{
			window.close();
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(closeButton,l);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout,250,250);
		window.setScene(scene);
		window.show();
		
	}

}

