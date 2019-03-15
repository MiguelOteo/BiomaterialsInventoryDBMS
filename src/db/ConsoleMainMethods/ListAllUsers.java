package db.ConsoleMainMethods;

import java.util.List;

import db.jdbc.SQLManager;
import db.model.UtilMethods;
import db.pojos.Client;
import db.pojos.Director;
import db.pojos.Worker;

public class ListAllUsers {

	public static void main(String[] args) {
		SQLManager manager = new SQLManager();
		UtilMethods methods = new UtilMethods();
		boolean everything_ok = manager.Stablish_connection();

		boolean tables_exist = manager.Check_if_tables_exist();
		if(tables_exist == true) {
		} else {
			everything_ok = manager.Create_tables();
		}
		
		if(everything_ok) {
			
			System.out.println("\n-----> CLIENT LIST <-----\n");
			List<Client> clients_list = manager.List_all_clients();
			for (Client client : clients_list) {
				System.out.print(client + "\n\n");
	        }
			System.out.println("-----> WORKER LIST<-----\n");
			List<Worker> worker_list = manager.List_all_workers();
			for (Worker worker : worker_list) {
				System.out.print(worker + "\n\n");
	        }
			System.out.println("-----> DIRECTOR LIST <-----\n");
			List<Director> director_list = manager.List_all_directors();
			for (Director director : director_list) {
				System.out.print(director + "\n\n");
	        }
		}
	}
}
