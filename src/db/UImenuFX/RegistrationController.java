package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class RegistrationController implements Initializable {

	// -----> CLASS ATRIBUTES <-----
	
	private ChargingScreenController charging_controller;
	
	// -----> FXML ATRIBUTES <-----

	@FXML
	private Pane registrationPane;
	@FXML
	private JFXComboBox<String> user_type;
	@FXML
	private JFXTextField userNameField;
	@FXML
	private JFXPasswordField passwordField;
	@FXML
	private JFXPasswordField repeatPasswordField;
	@FXML
	private JFXButton createAccountButton;
	@FXML
	private JFXButton goBackButton;
	@FXML
	private StackPane stack_pane;

	// -----> ESSENTIAL METHODS <-----

	public RegistrationController() {
		// Default constructor
	}

	public void initialize(URL location, ResourceBundle resources) {
		user_type.getItems().addAll("Client", "Director", "Worker");
		user_type.getSelectionModel().selectFirst();

		createAccountButton.setOnAction((ActionEvent event) -> {

			if (passwordField.getText().equals(repeatPasswordField.getText())) {
				try {
					String user_name = userNameField.getText();
					String password = passwordField.getText();
					String user_type_string = user_type.getSelectionModel().getSelectedItem();
					if (!(user_name.equals("") | password.equals(""))) {
						// Code to open charging window
						FXMLLoader loader = new FXMLLoader(getClass().getResource("ChargingScreenView.fxml"));
						Parent root = (Parent) loader.load();
						this.charging_controller = new ChargingScreenController();
						this.charging_controller = loader.getController();
						Stage stage = new Stage();
						stage.setOnShowing((event_handler) -> this.charging_controller.searching_create_account(user_name, password, user_type_string));
						stage.initStyle(StageStyle.UNDECORATED);
						stage.setAlwaysOnTop(true);
						stage.setScene(new Scene(root));
						stage.show();
						PauseTransition wait = new PauseTransition(Duration.seconds(2));
						wait.setOnFinished(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								if(charging_controller.getClientController() != null | charging_controller.getDirectorController() != null
										| charging_controller.getWorkerController() != null) {
									charging_controller.removeBlur();
								}
								stage.close();
							}
				        });
						wait.play();

						root = FXMLLoader.load(getClass().getResource("LogInView.fxml"));
						LaunchApplication.getStage().getScene().setRoot(root);
					}
				} catch (IOException charging_screen_error) {
					charging_screen_error.printStackTrace();
				}
			} else {
				passwordField.clear();
				repeatPasswordField.clear();
			}
		});
	}

	// -----> BUTTOM METHODS <-----

	@FXML // It is triggered when "go back" buttom is pressed
	private void back_to_menu(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LogInView.fxml"));
		LaunchApplication.getStage().getScene().setRoot(root);
	}

	@FXML // It is triggered when "extitCross.png" is pressed
	private void close_app(MouseEvent event) {
		System.exit(0);
	}
}

/*
 * JFXDialogLayout layout = new JFXDialogLayout(); layout.setHeading(new
 * Text("Account could not be created")); layout.setBody(new
 * Text("Passwords are not the same")); 
 * JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER); JFXButton
 * close_buttom = new JFXButton("Close"); close_buttom.setOnAction(new
 * EventHandler<ActionEvent>() {
 * 
 * @Override public void handle(ActionEvent event) { dialog.close(); } });
 * layout.setActions(close_buttom); Scene scene = new Scene(stack_pane, 300,
 * 250); LaunchApplication.stage.setScene(scene); dialog.show();
 * LaunchApplication.stage.show();
 */
