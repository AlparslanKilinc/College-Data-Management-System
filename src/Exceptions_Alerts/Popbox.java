package Exceptions_Alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Popbox {

	
	 public static void infoBox(String infoMessage, String titleBar, String headerMessage)
	    {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle(titleBar);
	        alert.setHeaderText(headerMessage);
	        alert.setContentText(infoMessage);
	        alert.showAndWait();
	    }
	 public static void errorBox(String infoMessage, String titleBar, String headerMessage)
	    {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle(titleBar);
	        alert.setHeaderText(headerMessage);
	        alert.setContentText(infoMessage);
	        alert.showAndWait();
	    }
	 public static void warningBox(String infoMessage, String titleBar, String headerMessage)
	    {
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle(titleBar);
	        alert.setHeaderText(headerMessage);
	        alert.setContentText(infoMessage);
	        alert.showAndWait();
	    }
	 
}

