package db.UImenuFX;


import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import db.jdbc.SQLManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ProductOptionController  implements Initializable {

	
	//-------> CLASS ATTRIBUTES <----------
	private static SQLManager manager_object;
	
	//-------> FXML ATTRIBUTES <----------
    @FXML
    private AnchorPane menu_window;
    @FXML
    private JFXButton newProduct_button;
    @FXML
    private JFXButton orderProduct_button;
    @FXML
	private static Stage stage_window;
    @FXML
    private ImageView exit_button;


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
				newProduct_controller.getMenu_window().setEffect(null);
				
				stage_window = new Stage();
				stage_window.initStyle(StageStyle.UNDECORATED);
				stage_window.setScene(new Scene(root));
				stage_window.setAlwaysOnTop(true);
				stage_window.show();
				
				Stage previous_stage = (Stage) menu_window.getScene().getWindow();
				previous_stage.close();
				
			} catch (IOException access_new_error) {
				access_new_error.printStackTrace();
			}
		});
		
		orderProduct_button.setOnAction((ActionEvent event) -> {
			try {
				OrderProductController.setValues(manager_object);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderProductView.fxml"));
				Parent root = (Parent) loader.load();
				OrderProductController order_controller = new OrderProductController();
				order_controller = loader.getController();
				order_controller.getOrder_pane().setEffect(null);
				
				stage_window = new Stage();
				stage_window.initStyle(StageStyle.UNDECORATED);
				stage_window.setScene(new Scene(root));
				stage_window.setAlwaysOnTop(true);
				stage_window.setOnShowing(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent arg0) {
						
						
					}
					
				});
				
				stage_window.show();
				//LaunchApplication.getStage().hide();
				/*Stage previous_stage = (Stage) menu_window.getScene().getWindow();
				previous_stage.close();*/
			
			} catch (IOException access_new_error) {
				access_new_error.printStackTrace();
			}
		});
		
		
		
		
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
	
	
	//------------> BUTTON METHODS <---------------
	
	@FXML
    void close_window(MouseEvent event) {
    	Stage stage = (Stage) menu_window.getScene().getWindow();
		stage.close();
    }

    
    
}

