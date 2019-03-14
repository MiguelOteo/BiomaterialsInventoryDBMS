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
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class ChargingScreenController implements Initializable {
	
	private StringProperty user_name = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private StringProperty user_type = new SimpleStringProperty();
	private ObjectProperty<SQLManager> manager;
	
    // -----> FXML ATRIBUTES <-----
	
	@FXML
    private Stage stage;

	// -----> ESSENTIAL METHODS <-----
	
	public ChargingScreenController() {
		// Default constructor
	}
	
	public ChargingScreenController(String user_name, String password, String user_type) throws IOException {
		this.user_name.set(user_name);
	    this.password.set(password);
	    this.user_type.set(user_type);
	    
	    // The following code charges all the database info and tables
	    manager = new SimpleObjectProperty<>(new SQLManager());
	    manager.getValue().Stablish_connection();
		boolean tables_exist = manager.getValue().Check_if_tables_exist();
		if(tables_exist == false) {
			manager.getValue().Create_tables();
		}
	    	
	       // List all clients in order to find if he exist to access it	 
		   List<Client> clients_list = manager.getValue().List_all_clients();
		   Client client_account = null;
		   for(Client client: clients_list) {
			   if((client.getName().equals(this.user_name.get())) && (client.getPassword().equals(this.password.get()))) {
				   client_account = client;
				   break;
			   }
		   }
		   if(client_account != null) {
			   if(this.user_type.get() == null) {
			       // TODO - charge client menu
			       // TODO - close windows
			   } else {
				   // TODO - close charging window
			   }
		   } else {
			   // List all directors in order to find if he exist to access it
			   List<Director> directors_list = manager.getValue().List_all_directors();
			   Director director_account = null;
			   for(Director director: directors_list) {
				   if((director.getDirector_name().equals(this.user_name.get())) && (director.getPassword().equals(this.password.get()))) {
					   director_account = director;
					   break;
				   }
			   }
			   if(director_account != null) {
				   if(this.user_type.get() == null) {
					      // TODO - charge director menu
					      // TODO - close windows
					   } else {
						   // TODO - close charging window
					   }
			   } else {
				   // List all workers in order to find if he exist to access it
				   List<Worker> workers_list = manager.getValue().List_all_workers();
				   Worker worker_account = null;
				   for(Worker worker: workers_list) {
					   if((worker.getWorker_name().equals(this.user_name.get())) && (worker.getPassword().equals(this.password.get()))) {
						   worker_account = worker;
						   break;
					   }
				   }
				   if(worker_account != null) {
					   if(this.user_type.get() == null) {
						      // TODO - charge worker menu
						      // TODO - close windows
						   } else {
							   // TODO - close charging window
						   }
				   } else {
				    	if(this.user_type.get().equals("Client")) {
				    		boolean insertion_ok = manager.getValue().Insert_new_client_account(this.user_name.get(), this.password.get());
				    		// TODO - charge client menu
				    	} else {
				    		if(this.user_type.get().equals("Director")) {
				    			boolean insertion_ok = manager.getValue().Insert_new_director(this.user_name.get(), this.password.get());
				    		    // TODO - charge director menu
				    		} else {
				    			if(this.user_type.get().equals("Worker")) {
				    				boolean insertion_ok = manager.getValue().Insert_new_worker(this.user_name.get(), this.password.get());
				    				// TODO - charge worker menu
				    			}
				    		}
				   }
			   }  
		   }
	    }
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO - if its needed
	}
	
	public void charge_client_main_menu() {
		
	}
}
