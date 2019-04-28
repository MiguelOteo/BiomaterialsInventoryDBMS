package db.UImenuFX;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import db.pojos.Client;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ProductOptionController implements Initializable {

	private static SQLManager manager_object;
	
	
    @FXML
    private AnchorPane menu_window;

    @FXML
    private JFXButton newProduct_button;

    @FXML
    private JFXButton orderProduct_button;
    @FXML
	private static Stage stage_window;


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
				
				stage_window = new Stage();
				stage_window.initStyle(StageStyle.UNDECORATED);
				stage_window.setScene(new Scene(root));
				stage_window.setAlwaysOnTop(true);				
				stage_window.setOnShowing(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent arg0) {
						menu_window.setEffect(new BoxBlur(3,3,3));
						setAllButtonsOn();
					}
				});
				
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						setAllButtonsOff();
						menu_window.setEffect(null);
					}
				});
				stage_window.show();
				
				newProduct_controller.getConclude_button().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						ChargeIconController.setValues(manager_object);
						FXMLLoader loader = new FXMLLoader(getClass().getResource("ChargeIconView.fxml"));
						try {
							Parent root = (Parent) loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ChargeIconController charge_controller = new ChargeIconController();
						charge_controller = loader.getController();
						
						menu_window.setEffect(null);
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
						menu_window.setEffect(new BoxBlur(3,3,3));
						setAllButtonsOn();
					}
				});
				
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						setAllButtonsOff();
						menu_window.setEffect(null);
					}
				});
				stage_window.show();
				
				
				Stage stage = (Stage) menu_window.getScene().getWindow();
				stage.close();
				
				
			} catch (NumberFormatException | NullPointerException | IOException access_new_error) {
				access_new_error.printStackTrace();
			}
			
		});
		
		orderProduct_button.setOnAction((ActionEvent event) -> {
			
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

