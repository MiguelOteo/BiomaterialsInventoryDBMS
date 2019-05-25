package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Client;
import db.pojos.Director;
import db.pojos.User;
import db.pojos.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChargingScreenController implements Initializable {

	// -----> CLASS ATRIBUTES <-----
	
	private String user_name;
	private String password;
	private String user_type;
	private SQLManager SQL_manager;
	private JPAManager JPA_manager;
	
	private DirectorMenuController director_controller;
	private ClientMenuController client_controller;
	private WorkerMenuController worker_controller;

	// -----> FXML ATRIBUTES <-----

	@FXML
	private static AnchorPane charging_main_pane;
	@FXML
	private static Stage main_menu_stage;

	// -----> ESSENTIAL METHODS <-----

	public ChargingScreenController() {
		// Default constructor
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO - if its needed
	}
	
	public void searching_create_account(String user_name, String password, String user_type) {
		try {
			this.user_name = user_name;
			this.password = password;
			this.user_type = user_type;
			
			// The following code charges all the database info and tables
			SQL_manager = new SQLManager();
			JPA_manager = new JPAManager();
			
			SQL_manager.Stablish_connection();
			JPA_manager.Stablish_connection();
	
			// Next algorithm checks if the user account already exist when you create a new one or in 
			// case you access, if the account exist to charge it in all the user's tables (Client, Director, Worker)
			User user = SQL_manager.Search_stored_user(this.user_name, this.password);
			if(user != null) {
				if(this.user_type == null) {
					Client client_account = SQL_manager.Search_stored_client(user);
					if(client_account != null) {
						charge_client_main_menu(client_account);
						LaunchApplication.getStage().hide();
					} else {
					    Director director_account = SQL_manager.Search_stored_director(user);
					    if(director_account != null) {
					    	charge_director_main_menu(director_account);
							LaunchApplication.getStage().hide();
					    } else {
					    	Worker worker_account = SQL_manager.Search_stored_worker(user);
					    	if(worker_account != null) {
					    		charge_worker_main_menu(worker_account);
					    		LaunchApplication.getStage().hide();
					    	} else {
					    		System.exit(0);
					    	}
					    }
					}
				} else {
					SQL_manager.Close_connection();
				}
			} else {
				if (this.user_type == null) {
					SQL_manager.Close_connection();
				} else {
					if (this.user_type.equals("Client")) {
						User new_user = SQL_manager.Insert_new_user(user_name, password);
                        Client client = SQL_manager.Insert_new_client(new_user);
                        charge_client_main_menu(client);
						LaunchApplication.getStage().hide();
					} else {
						if (this.user_type.equals("Director")) {
							User new_user = SQL_manager.Insert_new_user(user_name, password);
							Director director = SQL_manager.Insert_new_director(new_user);
							charge_director_main_menu(director);
							LaunchApplication.getStage().hide();
						} else { 
							if (this.user_type.equals("Worker")) {
								User new_user = SQL_manager.Insert_new_user(user_name, password);
								Worker worker = SQL_manager.Insert_new_worker(new_user);
								charge_worker_main_menu(worker);
								LaunchApplication.getStage().hide();
							} else {
								SQL_manager.Close_connection();
								System.exit(0);
							}
						}
					}
				}
			}
		} catch (Exception error_occur) {
			error_occur.printStackTrace();
			SQL_manager.Close_connection();
		}
	}
	
	// -----> GET AND SET METHODS <-----
	
	public ClientMenuController getClientController() {
		return this.client_controller;
	}
	
	public DirectorMenuController getDirectorController() {
	    return this.director_controller;	
	}
	
	public WorkerMenuController getWorkerController() {
		return this.worker_controller;
	}
	
	public static Stage getMain_menu_stage() {
		return main_menu_stage;
	}
	
	// -----> OTHER METHODS <-----

	public void charge_client_main_menu(Client client) {
		try {
			ClientMenuController.setValues(this.SQL_manager, client);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientMenuView.fxml"));
			Parent root = (Parent) loader.load();
			this.client_controller = loader.getController();
			this.client_controller.setClientName(client.getName());
			this.client_controller.setClientEmail(client.getEmail());
			this.client_controller.setClientTelephone(client.getTelephone());
			this.client_controller.setResponsible(client.getResponsible());
			this.client_controller.getAnchorPane().setEffect(new BoxBlur(4,4,4));
			main_menu_stage = new Stage();
			main_menu_stage.initStyle(StageStyle.UNDECORATED);
			main_menu_stage.setScene(new Scene(root));
			main_menu_stage.show();
		} catch (IOException client_menu_error) {
			client_menu_error.printStackTrace();
			System.exit(0);
		}
	}

	public void charge_director_main_menu(Director director) {
		try {
			DirectorMenuController.setValues(this.SQL_manager, this.JPA_manager, director);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DirectorMenuView.fxml"));
			Parent root = (Parent) loader.load();
			this.director_controller = loader.getController();
			this.director_controller.setDirectorName(director.getDirector_name());
			this.director_controller.setDirectorEmail(director.getEmail());
			this.director_controller.setDirectorTelephone(director.getTelephone());
			this.director_controller.getAnchorPane().setEffect(new BoxBlur(4,4,4));
			main_menu_stage = new Stage();
			main_menu_stage.initStyle(StageStyle.UNDECORATED);
			main_menu_stage.setScene(new Scene(root));
			main_menu_stage.show();
		} catch (IOException director_menu_error) {
			director_menu_error.printStackTrace();
			System.exit(0);
		}
	}

	public void charge_worker_main_menu(Worker worker) {
		try {
			WorkerMenuController.setValues(this.SQL_manager, worker);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WorkerMenuView.fxml"));
			Parent root = (Parent) loader.load();
			this.worker_controller = loader.getController();
			this.worker_controller.setWorkerName(worker.getWorker_name());
			this.worker_controller.setWorkerEmail(worker.getEmail());
			this.worker_controller.setWorkerTelephone(worker.getTelephone());
			this.worker_controller.getAnchorPane().setEffect(new BoxBlur(4,4,4));
			main_menu_stage = new Stage();
			main_menu_stage.initStyle(StageStyle.UNDECORATED);
			main_menu_stage.setScene(new Scene(root));
			main_menu_stage.show();
		} catch (IOException worker_menu_error) {
			worker_menu_error.printStackTrace();
			System.exit(0);
		}
	}
	
	public void removeBlur() {
		if(this.client_controller != null) {
			this.client_controller.getAnchorPane().setEffect(null);
		} else {
			if(this.director_controller != null) {
				this.director_controller.getAnchorPane().setEffect(null);
			} else {
				this.worker_controller.getAnchorPane().setEffect(null);
			}
		}
	}
}
