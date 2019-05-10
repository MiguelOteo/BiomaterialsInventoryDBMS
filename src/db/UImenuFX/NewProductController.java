package db.UImenuFX;

import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.converter.IntegerStringConverter;

public class NewProductController implements Initializable {
	
	// -----> CLASS ATTRIBUTES <-----
	
		private static SQLManager manager_object;
		private static WorkerMenuController worker_controller;
		private Biomaterial biomaterial;
		
	// -----> FXML ATTRIBUTES <-----
		
		@FXML
	    private Pane new_product_pane;
	    @FXML
	    private JFXButton conclude_button;
	    @FXML
	    private JFXComboBox<Integer> units_button;
	    @FXML
	    private JFXComboBox<Integer> price_button;
	    @FXML
	    private JFXTextField name_field;
	    @FXML
	    private JFXDatePicker date_picker;
	    @FXML
	    private JFXButton features_button;
	    @FXML
	    private static Stage stage_window;
	    
	
 // -----> ESSENTIAL METHODS <-----
    

	public NewProductController() {
		super();
	}
	
	
	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
	
	public Biomaterial getBiomaterial() {
		return biomaterial;
	}
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
			units_button.getItems().addAll(50, 100, 250, 500, 1000);
			price_button.getItems().addAll(10, 20,50, 80, 100);
    		units_button.setConverter(new IntegerStringConverter());
    		price_button.setConverter(new IntegerStringConverter());
    		
        /*ERROR NO SE ABRE*/
        features_button.setOnAction((ActionEvent) -> {
        	
        	try {
        		FeaturesController.setValue(manager_object);
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductFeaturesView.fxml"));
    			Parent root = (Parent) loader.load();
    			FeaturesController controller = new FeaturesController();
    			controller = loader.getController();
    			controller.getDone_button().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						worker_controller.getAnchorPane().setEffect(null);
						stage_window.close();
					}
				});
    			stage_window = new Stage();
				stage_window.initStyle(StageStyle.UNDECORATED);
				stage_window.setScene(new Scene(root));
				stage_window.setAlwaysOnTop(true);				
				stage_window.setOnShowing(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent arg0) {
						worker_controller.getAnchorPane().setEffect(new BoxBlur(3,3,3));
						stage_window.initModality(Modality.APPLICATION_MODAL);
					}
				});
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
					@Override
					public void handle(WindowEvent event) {
						worker_controller.getAnchorPane().setEffect(null);
					}
				});		
				stage_window.show();
    			
    			
    		} catch (IOException features_error) {
    			features_error.printStackTrace();
    			System.exit(0);
    		}
        });
        
       
        
        
        conclude_button.setOnAction((ActionEvent) -> {
        	
        	
        	String product_name = name_field.getText();
    		LocalDate exp_date = date_picker.getValue();
    		Integer units = units_button.getValue();
    		Integer price = price_button.getValue();
        	
    		if (!product_name.equals("")) {
    			Biomaterial biomaterial = new Biomaterial();
    			biomaterial.setName_product(product_name);
    			biomaterial.setAvailable_units(units.intValue());
    			biomaterial.setPrice_unit(price.intValue());
    			biomaterial.setExpiration_date(Date.valueOf(exp_date));
    			
    			
    			FeaturesController.setValue(manager_object);
    			Integer bio_id = manager_object.Insert_new_biomaterial(biomaterial);
    			System.out.println("Biomaterial: " + manager_object.Search_biomaterial_by_id(bio_id));
    			FeaturesController.setBiomaterial(manager_object.Search_biomaterial_by_id(bio_id));
    		}
        });
    	
    }
    
    
}

