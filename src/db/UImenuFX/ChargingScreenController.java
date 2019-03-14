package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import db.jdbc.SQLManager;
import db.pojos.Client;
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
		// Default contructor
	}
	
	public ChargingScreenController(String user_name, String password, String user_type) throws IOException {
		this.user_name.set(user_name);
	    this.password.set(password);
	    this.user_type.set(user_type);
	    
	    manager = new SimpleObjectProperty<>(new SQLManager());
	    manager.getValue().Stablish_connection();
		boolean tables_exist = manager.getValue().Check_if_tables_exist();
		if(tables_exist == false) {
			manager.getValue().Create_tables();
		}
	    
	    if(this.user_type.get() == null) {
		   List<Client> clients_list = manager.getValue().List_all_clients();
		   boolean client_exist = false;
		   for(Client client: clients_list) {
			   System.out.println(client);
			   if((client.getName().equals(this.user_name.get())) && (client.getPassword().equals(this.password.get()))) {
				   client_exist = true;
				   break;
			   }
		   }
		   if(client_exist == true) {
			   // TODO - charge client menu
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
	    				// TODO - charge worker manu
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
