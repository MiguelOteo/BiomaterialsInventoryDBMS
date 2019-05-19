package db.ConsoleMains;

import java.io.IOException;
import java.util.List;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Client;
import db.pojos.Director;
import db.pojos.User;
import db.pojos.Worker;

public class ListAllUsers {

	public static void main(String[] args) throws NumberFormatException, IOException {
		SQLManager manager = new SQLManager();
		JPAManager manager_2 = new JPAManager();
		boolean everything_ok = manager.Stablish_connection();
			
			System.out.println("-----> USER LIST <-----\n");
			List<User> users_list = manager.List_all_users();
			for (User user : users_list) {
				System.out.print(user + "\n\n");
	        }
			
			System.out.println("-----> DIRECTOR LIST <-----\n");
			List<Director> director_list = manager.List_all_directors();
			for (Director director : director_list) {
				System.out.print(director + "\n\n");
	        }
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
		}
}
