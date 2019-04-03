package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.jdbc.SQLManager;
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
	private SQLManager manager;
	
	private DirectorMenuController director_controller;
	private ClientMenuController client_controller;
	private WorkerMenuController worker_controller;

	// -----> FXML ATRIBUTES <-----

	@FXML
	public static AnchorPane charging_main_pane;
	@FXML
	public static Stage main_menu_stage;

	// -----> ESSENTIAL METHODS <-----

	public ChargingScreenController() {
		// Default constructor
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO - if its needed
	}
	
	// Next algorithm checks if the user account already exist when you create a new one or in 
	// case you access, if the account exist to charge it in all the user's tables (Client, Director, Worker)
	public void searching_create_account(String user_name, String password, String user_type) {
		try {
			this.user_name = user_name;
			this.password = password;
			this.user_type = user_type;
			
			// The following code charges all the database info and tables
			manager = new SQLManager();
			manager.Stablish_connection();
			boolean tables_exist = manager.Check_if_tables_exist();
			if (tables_exist == false) {
				manager.Create_tables();
			}

			Integer user_id = manager.Search_stored_user(this.user_name, this.password);
			if(user_id != null) {
				if(this.user_type == null) {
					Client client_account = manager.Search_stored_clients(user_id);
					if(client_account != null) {
						charge_client_main_menu(client_account);
						LaunchApplication.stage.hide();
					} else {
					    Director director_account = manager.Search_stored_director(user_id);
					    if(director_account != null) {
					    	charge_director_main_menu(director_account);
							LaunchApplication.stage.hide();
					    } else {
					    	Worker worker_account = manager.Search_stored_worker(user_id);
					    	if(worker_account != null) {
					    		charge_worker_main_menu(worker_account);
					    		LaunchApplication.stage.hide();
					    	} else {
					    		System.exit(0);
					    	}
					    }
					}
				} else {
					System.out.println("El usuario ya existe");
				}
			} else {
				if (this.user_type == null) {
					System.out.println("No existe ese usuario");
				} else {
					if (this.user_type.equals("Client")) {
						User user = manager.Insert_new_user(user_name, password);
                        Client client = manager.Insert_new_client(user);
                        charge_client_main_menu(client);
						LaunchApplication.stage.hide();
					} else {
						if (this.user_type.equals("Director")) {
							User user = manager.Insert_new_user(user_name, password);
							Director director = manager.Insert_new_director(user);
							charge_director_main_menu(director);
							LaunchApplication.stage.hide();
						} else {
							if (this.user_type.equals("Worker")) {
								// TODO - Insert Worker
		                        // TODO - Worker menu
								System.out.println("Creando trabajador");
							}
						}
					}
				}
			}
			manager.Close_connection();
		} catch (Exception error_occur) {
			error_occur.printStackTrace();
			manager.Close_connection();
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
	
	// -----> OTHER METHODS <-----

	public void charge_client_main_menu(Client client) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientMenuView.fxml"));
			Parent root = (Parent) loader.load();
			this.client_controller = new ClientMenuController(this.manager, client);
			this.client_controller = loader.getController();
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DirectorMenuView.fxml"));
			Parent root = (Parent) loader.load();
			this.director_controller = new DirectorMenuController(this.manager, director);
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WorkerMenuView.fxml"));
			Parent root = (Parent) loader.load();
			this.worker_controller = new WorkerMenuController(this.manager, worker);
			this.worker_controller = loader.getController();
			this.worker_controller.getAnchorPane().setEffect(new BoxBlur(4,4,4));
			main_menu_stage = new Stage();
			main_menu_stage.initStyle(StageStyle.UNDECORATED);
			main_menu_stage.setScene(new Scene(root));
			main_menu_stage.show();
		} catch (IOException director_menu_error) {
			director_menu_error.printStackTrace();
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
