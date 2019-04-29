package db.UImenuFX;

import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import db.pojos.Maintenance;
import db.pojos.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;

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
    
    
	
	
	
    @SuppressWarnings("unlikely-arg-type")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	//Set values of spinners
		SpinnerValueFactory<Integer> UnitsvalueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, 0);
        this.units_button.setValueFactory(UnitsvalueFactory);
        SpinnerValueFactory<Integer> PricevalueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, 0);
        this.price_button.setValueFactory(PricevalueFactory);
    	
    	
		conclude_button.setOnAction((ActionEvent event) -> {
			String product_name = name_field.getText();
			Integer units = (Integer) units_button.getValue();
			Integer price = (Integer) price_button.getValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate exp_date = date_picker.getValue();
			
			if (!product_name.equals("")) {
				
				Biomaterial biomaterial = new Biomaterial();
				biomaterial.setName_product(product_name);
				biomaterial.setAvailable_units(units);
				biomaterial.setPrice_unit(price);
				biomaterial.setExpiration_date(Date.valueOf(exp_date));
				biomaterial.setMaintenance(new Maintenance());
				biomaterial.setUtility(null);
				biomaterial.setMaintenance(null);
				
				if(!product_name.equals(manager_object.Search_stored_biomaterial(biomaterial))) {

					manager_object.Insert_new_biomaterial(biomaterial);
					System.out.println("Biomaterial saved into database");
		
				} else System.out.println("Product already existing");
			}
		});
		
		
    }

    
    
    
}

