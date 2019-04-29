package db.UImenuFX;


import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import db.jdbc.SQLManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProductOptionController  implements Initializable {

	
	//-------> CLASS ATTRIBUTES <----------
	private static SQLManager manager_object;
	private OrderProductController order_controller;
	
	//-------> FXML ATTRIBUTES <----------
    @FXML
    private AnchorPane menu_window;
    @FXML
    private JFXButton newProduct_button;
    @FXML
    private JFXButton orderProduct_button;


  //-------> GETTERS AND SETTERS <----------
    
    
  //-------> PRINCIPAL METHODS <----------
    
    public ProductOptionController() {
		// TODO Auto-generated constructor stub
	}
    
    public static void setValues(SQLManager manager) {
		manager_object = manager;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		newProduct_button.setOnAction((ActionEvent event) -> {
			
			try {
				NewProductController.setValues(manager_object);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("NewProductView.fxml"));
				Parent root = (Parent) loader.load();
				NewProductController newProduct_controller = new NewProductController();
				newProduct_controller = loader.getController();
				
				
				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.setAlwaysOnTop(true);
				stage.show();
				
				Stage previous_stage = (Stage) menu_window.getScene().getWindow();
				previous_stage.close();
				
			} catch (IOException access_new_error) {
				access_new_error.printStackTrace();
			}
		});
		
		orderProduct_button.setOnAction((ActionEvent event) -> {
			try {
				OrderProductController.setValues(manager_object);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("WorkerMenuView.fxml"));
				Parent root = (Parent) loader.load();
				order_controller = new OrderProductController();
				order_controller = loader.getController();
				
				Stage stage = new Stage();
				stage.setOnShowing((event_handler) -> {
					try {
						this.order_controller.open_order_panel(event_handler);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				stage.setAlwaysOnTop(true);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();
				
				Stage previous_stage = (Stage) menu_window.getScene().getWindow();
				previous_stage.close();
			
			} catch (IOException access_new_error) {
				access_new_error.printStackTrace();
			}
		});
		
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
	
    
}

