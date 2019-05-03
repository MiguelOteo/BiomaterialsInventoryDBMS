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
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class NewProductController implements Initializable {
	
	// -----> CLASS ATTRIBUTES <-----
	
		private static SQLManager manager_object;
		private static WorkerMenuController worker_controller;
		private static Biomaterial biomaterial;
		
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
	    @FXML
	    private static Stage stage_window;
	    
    
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
		
		
		
	
    
 // -----> METHODS <-----
    
	

	public NewProductController() {
		super();
	}
	
	public static void setValues(WorkerMenuController controller) {
		worker_controller = controller;
	}
	
	public static void setManager(SQLManager manager) {
		manager_object = manager;
	}
	
	public Biomaterial getBiomaterial() {
		return biomaterial;
	}
    
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	//Set values of spinners
		SpinnerValueFactory<Integer> UnitsvalueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, 0);
        this.units_button.setValueFactory(UnitsvalueFactory);
        SpinnerValueFactory<Integer> PricevalueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, 0);
        this.price_button.setValueFactory(PricevalueFactory);
        
        
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
    			/*
    			 stage = new Stage();
    			stage.initStyle(StageStyle.UNDECORATED);
    			stage.setScene(new Scene(root));
    			stage.setAlwaysOnTop(true);
    			stage.show();
    			*/
    			
    		} catch (IOException features_error) {
    			features_error.printStackTrace();
    			System.exit(0);
    		}
        });
        
        conclude_button.setOnAction((ActionEvent) -> {
        	String product_name = name_field.getText();
        		units_button.getEditor();
    		Integer units = (Integer)units_button.getValue();
    		System.out.println(units);
    		Integer price = price_button.getValue();
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		LocalDate exp_date = date_picker.getValue();
    		
    		if (!product_name.equals("")) {
    			Biomaterial biomaterial = new Biomaterial();
    			biomaterial.setName_product(product_name);
    			biomaterial.setAvailable_units(units);
    			biomaterial.setPrice_unit(price);
    			biomaterial.setExpiration_date(Date.valueOf(exp_date));
    			
    			
    			FeaturesController.setValue(manager_object);
    			Integer bio_id = manager_object.Insert_new_biomaterial(biomaterial);
    			System.out.println(bio_id + "nos echa el error aqui");
    			System.out.println("Biomaterial: " + manager_object.Search_biomaterial_by_id(bio_id));
    			FeaturesController.setBiomaterial(manager_object.Search_biomaterial_by_id(bio_id));
    		}
        });
    	
    }
    
    
}

