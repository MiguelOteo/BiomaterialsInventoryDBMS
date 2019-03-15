package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import db.jdbc.SQLManager;
import db.pojos.Client;
import db.pojos.Director;
import db.pojos.Worker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChargingScreenController implements Initializable {

	private StringProperty user_name = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private StringProperty user_type = new SimpleStringProperty();
	private ObjectProperty<SQLManager> manager;

	// -----> FXML ATRIBUTES <-----

	@FXML
	public static AnchorPane charging_main_pane;

	// -----> ESSENTIAL METHODS <-----

	public ChargingScreenController() {
		// Default constructor
	}

	public ChargingScreenController(String user_name, String password, String user_type) {
		try {
			this.user_name.set(user_name);
			this.password.set(password);
			this.user_type.set(user_type);

			// The following code charges all the database info and tables
			manager = new SimpleObjectProperty<>(new SQLManager());
			manager.getValue().Stablish_connection();
			boolean tables_exist = manager.getValue().Check_if_tables_exist();
			if (tables_exist == false) {
				manager.getValue().Create_tables();
			}

			// Next algorithm checks if the user account already exist when you create a new one or in 
			// case you access, if the account exist to charge it in all the user's tables (Client, Director, Worker)

			// List all clients in order to find if he exist to access it
			List<Client> clients_list = manager.getValue().List_all_clients();
			Client client_account = null;
			for (Client client : clients_list) {
				if ((client.getName().equals(this.user_name.get())) && (client.getPassword().equals(this.password.get()))) {
					client_account = client;
					break;
				}
			}
			if (client_account != null) {
				if (this.user_type.get() == null) {
					charge_client_main_menu(client_account);
					LaunchApplication.stage.hide();
					// TODO - close charging window
				} else {
					// TODO - close charging window
				}
			} else {
				// List all directors in order to find if he exist to access it
				List<Director> directors_list = manager.getValue().List_all_directors();
				Director director_account = null;
				for (Director director : directors_list) {
					if ((director.getDirector_name().equals(this.user_name.get())) && (director.getPassword().equals(this.password.get()))) {
						director_account = director;
						break;
					}
				}
				if (director_account != null) {
					if (this.user_type.get() == null) {
						charge_director_main_menu(director_account);
						LaunchApplication.stage.hide();
						// TODO - close charging window
					} else {
						// TODO - close charging window
						System.out.println("El director ya existe");
					}
				} else {
					// List all workers in order to find if he exist to access it
					List<Worker> workers_list = manager.getValue().List_all_workers();
					Worker worker_account = null;
					for (Worker worker : workers_list) {
						if ((worker.getWorker_name().equals(this.user_name.get()))
								&& (worker.getPassword().equals(this.password.get()))) {
							worker_account = worker;
							break;
						}
					}
					if (worker_account != null) {
						if (this.user_type.get() == null) {
							// TODO - charge worker menu
							LaunchApplication.stage.hide();
							// TODO - close charging window
							System.out.println("Cargando trabajador");
						} else {
							// TODO - close charging window
							System.out.println("El trabajador ya existe");
						}
					} else {
						if (this.user_type.get() == null) {
							// TODO - close charging window
							System.out.println("No existe ese usuario");
						} else {
							if (this.user_type.get().equals("Client")) {
								boolean insertion_ok = manager.getValue().Insert_new_client_account(this.user_name.get(), this.password.get());
								charge_client_main_menu(new Client(this.user_name.get(), this.password.get()));
								LaunchApplication.stage.hide();
								// TODO - close charging window
							} else {
								if (this.user_type.get().equals("Director")) {
									boolean insertion_ok = manager.getValue().Insert_new_director(this.user_name.get(), this.password.get());
									charge_director_main_menu(new Director(this.user_name.get(), this.password.get()));
									LaunchApplication.stage.hide();
									// TODO - close charging window
								} else {
									if (this.user_type.get().equals("Worker")) {
										boolean insertion_ok = manager.getValue().Insert_new_worker(this.user_name.get(), this.password.get());
										// TODO - charge worker menu
										LaunchApplication.stage.hide();
										// TODO - close charging window
										System.out.println("Creando trabajador");
									}
								}
							}
						}
					}
				}
			}
			manager.getValue().Close_connection();
		} catch (Exception error_occur) {
			error_occur.printStackTrace();
			manager.getValue().Close_connection();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO - if its needed
	}

	public void charge_client_main_menu(Client client) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientMenuView.fxml"));
			Parent root = (Parent) loader.load();
			ClientMenuController main_menu_controller = new ClientMenuController(this.manager, client);
			main_menu_controller = loader.getController();
			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException client_menu_error) {
			client_menu_error.printStackTrace();
			System.exit(0);
		}
	}

	public void charge_director_main_menu(Director director) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DirectorMenuView.fxml"));
			Parent root = (Parent) loader.load();
			DirectorMenuController main_menu_controller = new DirectorMenuController(this.manager, director);
			main_menu_controller = loader.getController();
			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException director_menu_error) {
			director_menu_error.printStackTrace();
			System.exit(0);
		}
	}

	public void charge_worker_main_menu(Worker worker) throws IOException {
		// try {

		// } catch (IOException worker_menu_error) {

		// }
	}
}
