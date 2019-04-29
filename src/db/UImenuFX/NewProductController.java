package db.UImenuFX;

import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewProductController implements Initializable {
	
	// -----> CLASS ATTRIBUTESS <-----
		private static SQLManager manager_object;
		
	// -----> FXML ATTRIBUTES <-----
		
		@FXML
	    private Pane new_product_pane;
	    @FXML
	    private JFXButton conclude_button;
	    @FXML
	    private Spinner<Integer> price_button;
	    @FXML
	    private Spinner<Integer> units_button;
	    @FXML
	    private JFXTextField name_field;
	    @FXML
	    private JFXDatePicker date_picker;
	    @FXML
	    private JFXButton features_button;
    
 // -----> GETTERS AND SETTERS <-----
    
	    
		public JFXTextField getName_field() {
			return name_field;
		}
		public Spinner<Integer> getPrice_button() {
			return price_button;
		}
		public void setPrice_button(Spinner<Integer> price_button) {
			this.price_button = price_button;
		}
		public Spinner<Integer> getUnits_button() {
			return units_button;
		}
		public void setUnits_button(Spinner<Integer> units_button) {
			this.units_button = units_button;
		}
		public void setName_field(JFXTextField name_field) {
			this.name_field = name_field;
		}
		public JFXDatePicker getDate_picker() {
			return date_picker;
		}
		public void setDate_picker(JFXDatePicker date_picker) {
			this.date_picker = date_picker;
		}
		public static void setValues(SQLManager manager) {
			manager_object = manager;
		}
	
    
 // -----> METHODS <-----
    
	

	public NewProductController() {
		super();
	}
    
    
	
	
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
		SpinnerValueFactory<Integer> UnitsvalueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, 0);
        this.units_button.setValueFactory(UnitsvalueFactory);
        SpinnerValueFactory<Integer> PricevalueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, 0);
        this.price_button.setValueFactory(PricevalueFactory);
    	
    	
		conclude_button.setOnAction((ActionEvent event) -> {
			try {
				String product_name = name_field.getText();
				Integer units = (Integer) units_button.getValue();
				Integer price = (Integer) price_button.getValue();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate exp_date = date_picker.getValue();
				System.out.println(exp_date);
				//Faltan los optativos checkBox para maintenance y utility
				
				Biomaterial biomaterial = manager_object.Search_biomaterial_by_id(null);
				biomaterial.setName_product(product_name);
				biomaterial.setAvailable_units(units);
				biomaterial.setPrice_unit(price);
				biomaterial.setExpiration_date(Date.valueOf(exp_date));
				//biomaterial.setMaintenance();
				//biomaterial.setUtility();
				
				
			if (!(product_name.equals(""))) {
				manager_object.Insert_new_biomaterial(biomaterial);
				System.out.println("Biomaterial saved into database");
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("NewProductView.fxml"));
				Parent root = (Parent) loader.load();
				WorkerMenuController worker_controller = new WorkerMenuController();
				worker_controller = loader.getController();
				worker_controller.getAnchorPane().setEffect(null);
					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setAlwaysOnTop(true);
					stage.setScene(new Scene(root));
					stage.show();
			
			
			
				this.name_field.clear();
			
			}
			} catch (IOException conclude_error) {
				conclude_error.printStackTrace();
			}
		});
		
		
    }

    
    
    
}

