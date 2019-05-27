package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UtilityInformationController {
	@FXML
	private JFXButton done_button;
	@FXML
	private Label heatcold_label;
	@FXML
	private Label flex_label;
	@FXML
	private Label res_label;
	@FXML
	private Label pres_label;
	@FXML
	private Label str_label;
	@FXML
	private AnchorPane confirmation_window;

	// -----> ESSENTIAL METHODS <-----
	
	public UtilityInformationController() {
		// TODO Auto-generated constructor stub
	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	// -----> BUTTON METHODS <-----
	@FXML
	public void yes_button() {
		
		
	}
	@FXML
	public void done_button() {
		Stage stage = (Stage) confirmation_window.getScene().getWindow();
		stage.close();
	}

	public Label getHeatcold_label() {
		return heatcold_label;
	}

	public void setHeatcold_label(String heatcold_label) {
		this.res_label.setText(heatcold_label);
	}

	public Label getFlex_label() {
		return flex_label;
	}

	public void setFlex_label(String flex_label) {
		this.res_label.setText(flex_label);
	}

	public Label getRes_label() {
		return res_label;
	}

	public void setRes_label(String res_label) {
		this.res_label.setText(res_label);
	}

	public Label getPres_label() {
		return pres_label;
	}

	public void setPres_label(String pres_label) {
		this.pres_label.setText(pres_label);
	}

	public Label getStr_label() {
		return str_label;
	}

	public void setStr_label(String str_label) {
		this.str_label.setText(str_label);
	}

	public JFXButton getNoButton() {
		return this.done_button;
	}

	
}