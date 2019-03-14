package db.UImenuFX;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import db.jdbc.SQLManager;
import db.pojos.Client;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;

public class ChargingScreenController implements Initializable {
	
	private StringProperty user_name = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private StringProperty user_type = new SimpleStringProperty();
	private ObjectProperty<SQLManager> manager;
	
    // -----> FXML ATRIBUTES <-----
	
    

	// -----> ESSENTIAL METHODS <-----
	
	public ChargingScreenController(String user_name, String password, String user_type) {
		this.user_name.set(user_name);
	    this.password.set(password);
	    this.user_type.set(user_type);
	    manager = new SimpleObjectProperty<>(new SQLManager());
	    boolean connection_ok = manager.getValue().Stablish_connection();
	    if(user_type == null) {
		   List<Client> clients_list = manager.getValue().List_all_clients();
	    } else {
	    	if(this.user_type.get().equals("Client")) {
	    		System.out.println("Its a client!!");   
	    	} else {
	    		if(this.user_type.get().equals("Director")) {
	    			System.out.println("Its a director!!");
	    		} else {
	    			if(this.user_type.get().equals("Worker")) {
	    				System.out.println("Its a worker!!");
	    			}
	    		}
	    	}
	    }
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO - if its needed
	}
}
