package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import db.jdbc.SQLManager;
import db.pojos.Client;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ClientMenuController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	private static Client client_account;
	private static SQLManager manager_object;

	// -----> FXML ATRIBUTES <-----

	@FXML
	private AnchorPane menu_window;
	@FXML
	private Pane main_pane;
	@FXML
	private Pane menu_main_pane;
	@FXML
	private JFXButton logOut_button;
	@FXML
	private JFXButton myAccount_button;
	@FXML
	private JFXButton marketplace_button;
	@FXML
	private JFXButton club_button;
	@FXML
	private JFXButton mainmenu_button;
	@FXML
	private Label client_name;
	@FXML
	private Label email;
	@FXML
	private Label telephone;
	@FXML
	private Label responsible;
	@FXML
	private ImageView minButton;
	@FXML
	private ImageView exitButton;
	@FXML
	private static Stage stage_window;
	
	// -----> ESSENTIAL METHODS <-----

	public ClientMenuController() {
		// TODO Auto-generated constructor stub
	}

	public static void setValues(SQLManager manager, Client client) {
		manager_object = manager;
		client_account = client;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setAllButtonsOn();
	    mainmenu_button.setDisable(true);
		try{
			Pane mainmenu_pane = FXMLLoader.load(getClass().getResource("ClientMainMenuView.fxml"));
		    main_pane.getChildren().removeAll();
		    main_pane.getChildren().setAll(mainmenu_pane);
		} catch (IOException client_view_error) {
			client_view_error.printStackTrace();
		}
		myAccount_button.setOnAction((ActionEvent) -> {
			try {
				AccountClientController.setValues(manager_object, client_account);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountClientView.fxml"));
				Parent root = (Parent) loader.load();
				AccountClientController account_controller = new AccountClientController();
				account_controller = loader.getController();
				account_controller.getDoneButton().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						update_client_account();
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
						stage_window.initModality(Modality.APPLICATION_MODAL);
					}
				});
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
					@Override
					public void handle(WindowEvent event) {
						menu_window.setEffect(null);
					}
				});		
				stage_window.show();
			} catch (IOException director_account_error) {
				director_account_error.printStackTrace();
				System.exit(0);
			}
		});}
	
	// -----> BUTTOM METHODS <-----

	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void log_out(MouseEvent event) {
		    manager_object.Close_connection();
			LaunchApplication.getStage().show();
			Stage stage = (Stage) logOut_button.getScene().getWindow();
			stage.close();
	}
	@FXML
	private void min_window(MouseEvent event) {
		Stage stage = (Stage) menu_main_pane.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	private void open_marketplace(MouseEvent event) {
		// try {

		// } catch (IOException marketplace_charge_error) {
      
		// }
	}
	
	@FXML
	private void loadmarketplace(MouseEvent event) throws IOException {
		if(client_account.getBank_account() != null) {
			setAllButtonsOn();
			marketplace_button.setDisable(true);
			MarketplaceController.setValues(manager_object,client_account);
			Pane marketplace_pane = FXMLLoader.load(getClass().getResource("MarketplaceView.fxml"));
			main_pane.getChildren().removeAll();
			main_pane.getChildren().setAll(marketplace_pane);
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NoBankAccountView.fxml"));
			Parent root = (Parent) loader.load();
			stage_window = new Stage();
			stage_window.initStyle(StageStyle.UNDECORATED);
			stage_window.setScene(new Scene(root));
			stage_window.setAlwaysOnTop(true);	
			stage_window.setOnShowing(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					menu_window.setEffect(new BoxBlur(3,3,3));
					stage_window.initModality(Modality.APPLICATION_MODAL);
				}
			});
			stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
				@Override
				public void handle(WindowEvent event) {
					menu_window.setEffect(null);
				}
			});
			stage_window.show();
		}
	}
	
	@FXML 
	private void openClub (MouseEvent event) throws IOException{
		setAllButtonsOn();
	    club_button.setDisable(true);
		Pane bengclub_pane = FXMLLoader.load(getClass().getResource("BengClubView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(bengclub_pane);
	}
	
	@FXML 
	private void openmainmenu (MouseEvent event) throws IOException{
		setAllButtonsOn();
	    mainmenu_button.setDisable(true);
		Pane mainmenu_pane = FXMLLoader.load(getClass().getResource("ClientMainMenuView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(mainmenu_pane);
	}
	
	// -----> SET AND GET METHODS <-----
	
	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
	
	public void setClientTelephone(Integer telephone) {
		if (telephone == null) {
			this.telephone.setText("Telephone: No telephone associated");
		} else {
			if (telephone != 0) {
				this.telephone.setText("Telephone: " + telephone);
			} else {
				this.telephone.setText("Telephone: No telephone associated");
			}
		}
	}

	public void setClientName(String name) {
		this.client_name.setText("Client's name: " + name );
	}

	public void setClientEmail(String email) {
		if (email != null) {
			this.email.setText("Email: " + email);
		} else {
			this.email.setText("Email: No email associated");
		}
	}
	
	public void setResponsible(String responsible) {
		if (responsible != null) {
			this.responsible.setText("Responsible: " + responsible);
		} else {
			this.responsible.setText("Responsible: No one associated");
		}
	}
	
	// -----> ANABLE/DISABLE BUTTONS <-----
	
	protected void setAllButtonsOff() {
		 myAccount_button.setDisable(true);
		    marketplace_button.setDisable(true);
		    club_button.setDisable(true);
		    mainmenu_button.setDisable(true);
		    logOut_button.setDisable(true);
		    minButton.setDisable(true);
		    exitButton.setDisable(true);
	}
	
	protected void setAllButtonsOn() {
		 myAccount_button.setDisable(false);
		    marketplace_button.setDisable(false);
		    club_button.setDisable(false);
		    mainmenu_button.setDisable(false);
		    logOut_button.setDisable(false);
		    minButton.setDisable(false);
		    exitButton.setDisable(false);
	}
	
	// -----> UPDATE ACCOUNT METHOD <-----
	
	public void update_client_account() {
		client_account = manager_object.Search_client_by_id(client_account.getClient_id());
    	setClientEmail(client_account.getEmail());
    	setClientName(client_account.getName());
		setClientTelephone(client_account.getTelephone());
		setResponsible(client_account.getResponsible());
	}
}

