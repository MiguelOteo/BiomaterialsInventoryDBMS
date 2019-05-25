package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import db.jdbc.SQLManager;
import db.pojos.Maintenance;
import db.pojos.Utility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class NewFeaturesController implements Initializable{

	// -----------> CLASS ATTRIBUTES <-----------------

	private static SQLManager manager_object;
	
	// -----------> FXML ATTRIBUTES <-----------------
	

    @FXML
    private Label instructions_label;
	@FXML
    private JFXButton doneUtility_button;
	@FXML
    private JFXButton doneMaintenance_button;
    @FXML
    private JFXComboBox<String> tempcondition;
    @FXML
    private JFXComboBox<String> flexibility;
    @FXML
    private JFXComboBox<String> resistance;
    @FXML
    private JFXTextField pressure;
    @FXML
    private JFXTextField strength;
    @FXML
    private JFXTextField pressureM;
    @FXML
    private JFXTextField humidityM;
    @FXML
    private JFXTextField tempM;
    @FXML
    private JFXComboBox<String> o2supplyM;
    @FXML
    private JFXComboBox<String> lightM;
    @FXML
    private JFXTextField compatibilityM;
    @FXML
    private JFXTextArea othersM;
    @FXML
    private JFXButton utility_button;
    @FXML
    private JFXButton maintenance_button;

	// -----------> CLASS ATTRIBUTES <-----------------
	
	public NewFeaturesController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void setValue(SQLManager manager) {
		manager_object = manager;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setMaintenanceBoxesOff();
		setUtilityBoxesOff();
		instructions_label.setVisible(true);
		instructions_label.setText("Click on features to be added");
		
		flexibility.getItems().addAll("Yes", "No");
		lightM.getItems().addAll("Yes", "No");
		o2supplyM.getItems().addAll("Yes", "No");
		resistance.getItems().addAll("Yes", "No");
		tempcondition.getItems().addAll("Heat", "Cold");
		
		
	}
	
	// -----------> BUTTON METHODS <-----------------
	
	@FXML
	public void showUtilities(MouseEvent event) {
		if (doneUtility_button.isDisable()) {
			setUtilityBoxesOn();
			instructions_label.setVisible(false);
		} else setUtilityBoxesOff();
		
	}
	@FXML
	public void showMaintenance(MouseEvent event) {
		if (doneMaintenance_button.isDisable()) {
			setMaintenanceBoxesOn();
			instructions_label.setVisible(false);
		} else setMaintenanceBoxesOff();
	}
	
	@FXML
	public void doneUtility_button(MouseEvent event) {
		Utility utility = new Utility(tempcondition.getValue(), flexibility.getValue(), resistance.getValue(), 
				Float.parseFloat(pressure.getText()), Float.parseFloat(strength.getText()));
		manager_object.Insert_new_utility(utility);
	}
	@FXML
	public void doneMaintenance_button(MouseEvent event) {
		Maintenance maintenance = new Maintenance(Float.parseFloat(pressureM.getText()), o2supplyM.getValue(), lightM.getValue(), 
				Integer.parseInt(humidityM.getText()), Float.parseFloat(tempM.getText()), compatibilityM.getText(), othersM.getText());
		manager_object.Insert_new_maintenance(maintenance);
	}
	
	// -----------> METHODS <-----------------
	
	private void setUtilityBoxesOff() {
		flexibility.setDisable(true);
		doneUtility_button.setDisable(true);
		pressure.setDisable(true);
		resistance.setDisable(true);
		strength.setDisable(true);
		tempcondition.setDisable(true);
		
	}
	
	private void setMaintenanceBoxesOff() {
		compatibilityM.setDisable(true);
		doneMaintenance_button.setDisable(true);
		humidityM.setDisable(true);
		lightM.setDisable(true);
		o2supplyM.setDisable(true);
		othersM.setDisable(true);
		pressureM.setDisable(true);
		tempM.setDisable(true);
		
	}
	
	private void setUtilityBoxesOn() {
		flexibility.setDisable(false);
		doneUtility_button.setDisable(false);
		pressure.setDisable(false);
		resistance.setDisable(false);
		strength.setDisable(false);
		tempcondition.setDisable(false);
	}
	
	private void setMaintenanceBoxesOn() {
		compatibilityM.setDisable(false);
		doneMaintenance_button.setDisable(false);
		humidityM.setDisable(false);
		lightM.setDisable(false);
		o2supplyM.setDisable(false);
		othersM.setDisable(false);
		pressureM.setDisable(false);
		tempM.setDisable(false);
	}

	
	
}
