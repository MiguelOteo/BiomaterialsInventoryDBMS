package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import db.jdbc.SQLManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OrderProductController implements Initializable {

	// -----> CLASS ATRIBUTES <-----
	
	private static SQLManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
    private AnchorPane menu_window;
    @FXML
    private Pane worker_menu_pane;
    @FXML
    private Pane order_pane;
    @FXML
    private Pane useless_panel;
    @FXML
    private JFXButton doOrder_button;
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
    private JFXButton logOut_button;
    @FXML
    private JFXButton myAccount_button;
    @FXML
    private JFXButton listInventory_button;
    @FXML
    private JFXButton addProduct_button;
    @FXML
    private JFXButton removeProduct_button;
    @FXML
    private JFXButton listTransactions_button;
    @FXML
    private JFXButton listClients_button;
	
	// -----> GETTERS AND SETTERS <-----
	
    public AnchorPane getMenu_window() {
		return menu_window;
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
	
	public Pane getUseless_panel() {
		return useless_panel;
	}

	public void setUseless_panel(Pane useless_panel) {
		this.useless_panel = useless_panel;
	}

	public JFXButton getDoOrder_button() {
		return doOrder_button;
	}

	public void setDoOrder_button(JFXButton doOrder_button) {
		this.doOrder_button = doOrder_button;
	}
	
		
	// -----> ESSENTIAL METHODS <-----
	
	
	
	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
	public SQLManager getValues() {
		return this.manager_object;
	}
	

	public OrderProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	// -----> BUTTON METHODS <-----
	
	@FXML
    void close_app(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void log_out(MouseEvent event) {
    	LaunchApplication.getStage().show();
		Stage stage = (Stage) logOut_button.getScene().getWindow();
		stage.close();
    }

    @FXML
    void min_window(MouseEvent event) {
    	Stage stage = (Stage) worker_menu_pane.getScene().getWindow();
		stage.setIconified(true);
    }

	
}
