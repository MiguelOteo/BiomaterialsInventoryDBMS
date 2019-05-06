package db.UImenuFX;

import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import db.pojos.Director;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MarketplaceController implements Initializable {
	
	private static SQLManager manager;
	private static MarketplaceController marketplace_controller;
	
	
	
	@FXML
	private AnchorPane menu_window;
	@FXML
	private Pane menu_main_pane;
	@FXML
	private Label prueba;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			/*
			
			
			*/
			Stage stage = (Stage) menu_window.getScene().getWindow();
			stage.close();
			
		} catch (Exception insertion_error) {
			insertion_error.printStackTrace();
		}
		
	
}
    
	public static void setValues(SQLManager manager2) {
		manager2 = manager2;
	}

}
