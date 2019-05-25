package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import db.jpa.JPAManager;
import db.pojos.Category;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class NoCategoryController implements Initializable{

	private static JPAManager manager_object;
	
	@FXML
	private JFXTextField max_point_field;
	@FXML
	private JFXButton summit_information;
	
	public NoCategoryController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(JPAManager manager) {
		manager_object = manager;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
	}
	
	// -----> GET AND SET METHODS <-----
	
	@FXML
	public void summit_information() {
		try {
			Integer max_points = Integer.parseInt(max_point_field.getText());
			Category category = new Category("None", max_points, 0);
			manager_object.Insert_new_category(category);
			Stage stage = (Stage) summit_information.getScene().getWindow();
			stage.close();
		} catch (NullPointerException | NumberFormatException number_error) {
			number_error.printStackTrace();
		}
	}
}
