package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import db.jdbc.SQLManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class OrderProductController implements Initializable {

	// -----> CLASS ATRIBUTES <-----
	@SuppressWarnings("unused")
	private static SQLManager manager_object;
	
	
	// -----> FXML ATRIBUTES <-----
	
	 @FXML
	 private Pane order_panel;
	 @FXML
	 private JFXButton doOrder_button;
	 @FXML
	 private JFXTreeTableView<SelectionListObject> selection_tree_view;

	// -----> GETTERS AND SETTERS <-----
	

	public JFXButton getDoOrder_button() {
		return doOrder_button;
	}

	public void setDoOrder_button(JFXButton doOrder_button) {
		this.doOrder_button = doOrder_button;
	}
	
		
	// -----> ESSENTIAL METHODS <-----
	
	public OrderProductController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	
	
	
	
}


//-----> SELECTION LIST CLASS <-----

//To insert columns into the list of selected biomaterials with all the information
class SelectionListObject extends RecursiveTreeObject<SelectionListObject> {
	StringProperty product_name;
	StringProperty available_units;
	StringProperty price_unit;
	StringProperty expiration_date;

	public SelectionListObject(String product_name, String available_units) {
		this.product_name = new SimpleStringProperty(product_name);
		this.available_units = new SimpleStringProperty(available_units);
	}
}

