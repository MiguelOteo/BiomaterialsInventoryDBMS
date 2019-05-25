package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import db.jpa.JPAManager;
import db.pojos.Benefits;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InsertNewBenefitController implements Initializable{
	
	// -----> CLASS ATRIBUTES <-----
	
	private static JPAManager JPA_manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private AnchorPane account_window;
	@FXML
	private JFXTextField benefits_percentage;
	@FXML
	private JFXTextField benefits_extra_unit;
	
	// -----> ESSENTIAL METHODS <-----
	
	public InsertNewBenefitController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(JPAManager JPA_manager) {
		JPA_manager_object = JPA_manager;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	// -----> BUTTON METHODS <-----
	
	@FXML
	private void insert_benefits() {
		try {
			Float percentage = Float.parseFloat(benefits_percentage.getText());
			Integer extra_units = Integer.parseInt(benefits_extra_unit.getText());
			Benefits benefits = new Benefits();
			benefits.setPercentage(percentage);
			benefits.setExtra_units(extra_units);
			JPA_manager_object.Insert_new_benefit(benefits);
			benefits_percentage.setText("");
			benefits_extra_unit.setText("");
		} catch (NumberFormatException | NullPointerException insertion_error) {
			benefits_percentage.setText("");
			benefits_extra_unit.setText("");
		}
	}
	
	@FXML
	private void close_window() {
		Stage stage = (Stage) account_window.getScene().getWindow();
		stage.close();
	}
}
