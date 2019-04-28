package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import db.jdbc.SQLManager;
import db.pojos.Worker;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class OrderProductController implements Initializable {

	// -----> CLASS ATRIBUTES <-----
	
	private static SQLManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
    private AnchorPane menu_window;
	@FXML
	private Pane worker_main_menu;
    @FXML
    private Pane worker_menu_pane;
    @FXML
    private Pane order_pane;
    @FXML
    private JFXButton doOrder_button;
    @FXML
    private JFXTreeTableView<SelectionListObject> selection_tree_view;
    @FXML
    private Label current_option_label;
    @FXML
    private ImageView exitButton;
    @FXML
    private ImageView minButton;
    @FXML
    private Label worker_name;
    @FXML
    private Label email;
    @FXML
    private Label telephone;
    @FXML
    private Stage stage;

	// -----> GETTERS AND SETTERS <-----
	
    
    
    public AnchorPane getMenu_window() {
		return menu_window;
	}

	public Pane getWorker_main_menu() {
		return worker_main_menu;
	}

	public void setWorker_main_menu(Pane worker_main_menu) {
		this.worker_main_menu = worker_main_menu;
	}

	public Pane getWorker_menu_pane() {
		return worker_menu_pane;
	}

	public void setWorker_menu_pane(Pane worker_menu_pane) {
		this.worker_menu_pane = worker_menu_pane;
	}

	public void setMenu_window(AnchorPane menu_window) {
		this.menu_window = menu_window;
	}
	
	public Pane getOrder_pane() {
		return order_pane;
	}
	public void setOrder_pane(Pane order_pane) {
		this.order_pane = order_pane;
	}

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
	
	
	@FXML
	public void open_order_panel(WindowEvent event_handler) throws IOException {
		Pane menu_panel = FXMLLoader.load(getClass().getResource("OrderProductView.fxml"));
		this.worker_main_menu.getChildren().removeAll();
		this.worker_main_menu.getChildren().setAll(menu_panel);
	}
	
	
	// -----> BUTTON METHODS <-----
	
	@FXML
    void close_app(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void min_window(MouseEvent event) {
    	Stage stage = (Stage) worker_menu_pane.getScene().getWindow();
		stage.setIconified(true);
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

