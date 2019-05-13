package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;

import db.jdbc.SQLManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class AddPromotionController implements Initializable{

	@FXML
	private JFXCheckBox bronze_3;
	@FXML
	private JFXCheckBox bronze_2;
	@FXML
	private JFXCheckBox bronze_1;
	@FXML
	private JFXCheckBox silver_2;
	@FXML
	private JFXCheckBox silver_1;
	@FXML
	private JFXCheckBox gold_2;
	@FXML
	private JFXCheckBox gold_1;
	@FXML
	private JFXCheckBox platinum_2;
	@FXML
	private JFXCheckBox platinum_1;
	@FXML
	private JFXCheckBox diamond_2;
	@FXML
	private JFXCheckBox diamond_1;
	@FXML
	private Pane main_pane;
	
	@SuppressWarnings("unused")
	private static SQLManager manager_object;
	
	public AddPromotionController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

}
