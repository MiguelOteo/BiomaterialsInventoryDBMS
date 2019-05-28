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
	private Label enpres_label;
	@FXML
	private Label o2_label;
	@FXML
	private Label light_label;
	@FXML
	private Label hum_label;
	@FXML
	private Label temp_label;
	@FXML
	private Label comp_label;
	@FXML
	private Label oth_label;
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
		this.heatcold_label.setText(heatcold_label);
	}

	public Label getFlex_label() {
		return flex_label;
	}

	public void setFlex_label(String flex_label) {
		this.flex_label.setText(flex_label);
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
	public Label getEnpres_label() {
		return enpres_label;
	}

	public void setEnpres_label(String enpres_label) {
		this.enpres_label.setText(enpres_label);
	}

	public Label getO2_label() {
		return o2_label;
	}

	public void setO2_label(String o2_label) {
		this.o2_label.setText(o2_label);
	}

	public Label getLight_label() {
		return light_label;
	}

	public void setLight_label(String light_label) {
		this.light_label.setText(light_label);
	}

	public Label getHum_label() {
		return hum_label;
	}

	public void setHum_label(String hum_label) {
		this.hum_label.setText(hum_label);
	}

	public Label getTemp_label() {
		return temp_label;
	}

	public void setTemp_label(String temp_label) {
		this.temp_label.setText(temp_label);
	}

	public Label getComp_label() {
		return comp_label;
	}

	public void setComp_label(String comp_label) {
		this.comp_label.setText(comp_label);
	}

	public Label getOth_label() {
		return oth_label;
	}

	public void setOth_label(String oth_label) {
		this.oth_label.setText(oth_label);
	}

	
}