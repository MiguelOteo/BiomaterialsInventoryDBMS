package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LogInController implements Initializable {
	
	// -----> CLASS ATRBUTES <-----
	
	
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private AnchorPane startUpMenu;
	@FXML
	private Pane logInPane;
	
    // -----> ESSENTIAL METHODS <-----

	public LogInController() {

	}
	
	public void initialize(URL location, ResourceBundle resources) {
       // TODO		
	}

	// -----> BUTTOM METHODS <-----
	
    @FXML
	private void open_registration (MouseEvent event) throws IOException {
		Pane registration_pane_fxml = FXMLLoader.load(getClass().getResource("RegistrationView.fxml"));
		logInPane.getChildren().removeAll(); 
		logInPane.getChildren().setAll(registration_pane_fxml);
	}
    
    @FXML // It is triggered when "extitCross.png" is pressed
    private void close_app (MouseEvent event) throws IOException {
    	System.exit(0);
    }
    
    // -----> GET METHODS FOR TEXT JFXFIELDS <-----
}
