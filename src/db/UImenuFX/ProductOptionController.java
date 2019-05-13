package db.UImenuFX;


import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import db.jdbc.SQLManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ProductOptionController  implements Initializable {

	
	//-------> CLASS ATTRIBUTES <----------
	
	private static SQLManager manager_object;
	@SuppressWarnings("unused")
	private NewProductController newProductController;
	@SuppressWarnings("unused")
	private OrderProductController orderProductController;
	
	//-------> FXML ATTRIBUTES <----------
	@FXML
    private Pane main_pain;
    @FXML
    private JFXButton newProduct_button;
    @FXML
    private JFXButton orderProduct_button;

    
    
  //-------> PRINCIPAL METHODS <----------
    
    public ProductOptionController() {
		// TODO Auto-generated constructor stub
	}
    
    public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
    
    public static SQLManager getManager() {
    	return manager_object;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	

	//--------------> BUTTON METHODS <-------------------
	
	@FXML
	public void open_order_panel(MouseEvent event) throws IOException {
		OrderProductController.setValues(manager_object);
		Pane order_pane = FXMLLoader.load(getClass().getResource("OrderProductView.fxml"));
		main_pain.getChildren().removeAll();
		main_pain.getChildren().setAll(order_pane);
	}

	@FXML
	public void open_newProduct_panel(MouseEvent event) throws IOException {
		NewProductController.setValues(manager_object);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NewProductView.fxml"));
		Pane newProduct_pane = loader.load();
		newProductController = (NewProductController) loader.getController();
		main_pain.getChildren().removeAll();
		main_pain.getChildren().setAll(newProduct_pane);
	}
    
}

