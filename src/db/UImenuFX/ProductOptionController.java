package db.UImenuFX;


import java.io.IOException;


import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProductOptionController  implements Initializable {

	
	//-------> CLASS ATTRIBUTES <----------
	
	private static WorkerMenuController worker_controller;
	
	//-------> FXML ATTRIBUTES <----------
    @FXML
    private AnchorPane menu_window;
    @FXML
    private JFXButton newProduct_button;
    @FXML
    private JFXButton orderProduct_button;


 
    
    
  //-------> PRINCIPAL METHODS <----------
    
    public ProductOptionController() {
		// TODO Auto-generated constructor stub
	}
    
    public static void setValues(WorkerMenuController controller) {
		worker_controller = controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	@FXML
	public void close_window(MouseEvent event) {
    	Stage stage = (Stage) this.menu_window.getScene().getWindow();
		stage.close();
    }
	

	
    //------------> GETTERS AND SETTERS <---------------
	
	
    public JFXButton getNewProduct_button() {
		return newProduct_button;
	}

	public void setNewProduct_button(JFXButton newProduct_button) {
		this.newProduct_button = newProduct_button;
	}

	public JFXButton getOrderProduct_button() {
		return orderProduct_button;
	}

	public void setOrderProduct_button(JFXButton orderProduct_button) {
		this.orderProduct_button = orderProduct_button;
	}

	
	
	
	// -----> ANABLE/DISABLE BUTTONS <-----
	
	public void setAllButtonsOff() {
		this.newProduct_button.setDisable(true);
		this.orderProduct_button.setDisable(true);
	}
			
	public void setAllButtonsOn() {
		this.newProduct_button.setDisable(false);
		this.orderProduct_button.setDisable(false);
	}
	

	@FXML
	public void open_order_panel(MouseEvent event) throws IOException {
		Pane menu_panel = FXMLLoader.load(getClass().getResource("OrderProductView.fxml"));
		worker_controller.getWorker_main_panel().getChildren().removeAll();
		worker_controller.getWorker_main_panel().getChildren().setAll(menu_panel);
	}

	@FXML
	public void open_newProduct_panel(MouseEvent event) throws IOException {
		Pane menu_panel = FXMLLoader.load(getClass().getResource("NewProductView.fxml"));
		worker_controller.getWorker_main_panel().getChildren().removeAll();
		worker_controller.getWorker_main_panel().getChildren().setAll(menu_panel);
	}
    
}

